// ActivityService.java
// Core business logic for the Activity Service.
// Handles activity tracking (including user validation), persistence to MongoDB, and publishing to RabbitMQ.
package com.fitness.activityservice.service;

import com.fitness.activityservice.ActivityRepository;
import com.fitness.activityservice.dto.ActivityRequest;
import com.fitness.activityservice.dto.ActivityResponse;
import com.fitness.activityservice.model.Activity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service // = "Spring manages this class as a service bean — it is the business logic layer, injected into controllers"
@RequiredArgsConstructor // = "Lombok generates a constructor with required arguments (final fields); Spring uses it for dependency injection"
@Slf4j // = "Lombok injects a SLF4J logger (log) into this class for structured logging"
public class ActivityService {

    // Data access layer for MongoDB — performs CRUD on Activity documents.
    private final ActivityRepository activityRepository;
    // Service that validates whether a user exists via an external HTTP call.
    private final UserValidationService userValidationService;
    // RabbitMQ template used to publish activity events to the message queue for downstream AI processing.
    private final RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.exchange.name}") // = "Injects the RabbitMQ exchange name from application.properties/yml configuration"
    private String exchange;

    @Value("${rabbitmq.routing.key}") // = "Injects the RabbitMQ routing key from configuration"
    private String routingKey;

    // Orchestrates the full activity tracking flow: validate user, build domain object, persist, publish, and return response.
    public ActivityResponse trackActivity(ActivityRequest request) {

        // Step 1: Validate that the user exists via the external User Service API.
        boolean isValidUser = userValidationService.validateUser(request.getUserId());
        // If the user is not found or invalid, throw a runtime exception to abort the request.
        if (!isValidUser) {
            throw new RuntimeException("Invalid User: " + request.getUserId());
        }

        // Step 2: Build an Activity domain object from the request data using the Lombok builder pattern.
        Activity activity = Activity.builder()
                .userId(request.getUserId())
                .type(request.getType())
                .duration(request.getDuration())
                .caloriesBurned(request.getCaloriesBurned())
                .startTime(request.getStartTime())
                .additionalMetrics(request.getAdditionalMetrics())
                .build();

        // Step 3: Persist the activity to MongoDB.
        Activity savedActivity = activityRepository.save(activity);

        // Step 4: Publish the saved activity to RabbitMQ so the AI Service can consume it for recommendations.
        // The try-catch ensures that a RabbitMQ failure does not break the main flow — the activity is still saved.
        try {
            rabbitTemplate.convertAndSend(exchange, routingKey, savedActivity);
        } catch(Exception e) {
            log.error("Failed to publish activity to RabbitMQ : ", e);
        }

        // Step 5: Convert the persistent entity back to a response DTO and return it.
        return mapToResponse(savedActivity);
    }

    // Private helper: maps an Activity entity to an ActivityResponse DTO by copying all fields.
    // This separates the internal persistence model from the external API contract.
    private ActivityResponse mapToResponse(Activity activity){
        ActivityResponse response = new ActivityResponse();
        response.setId(activity.getId());
        response.setUserId(activity.getUserId());
        response.setType(activity.getType());
        response.setDuration(activity.getDuration());
        response.setCaloriesBurned(activity.getCaloriesBurned());
        response.setStartTime(activity.getStartTime());
        response.setAdditionalMetrics(activity.getAdditionalMetrics());
        response.setCreatedAt(activity.getCreatedAt());
        response.setUpdatedAt(activity.getUpdatedAt());
        return response;
    }

    // Retrieves all activities for a given user — maps each entity to its response DTO.
    public List<ActivityResponse> getUserActivities(String userId) {
        // Fetch activities from the repository.
        List<Activity> activities = activityRepository.findByUserId(userId);
        // Stream over the list, map each entity to a response DTO, and collect into a new list.
        return activities.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // Retrieves a single activity by its MongoDB ID — throws if not found.
    public ActivityResponse getActivityById(String activityId) {
        return activityRepository.findById(activityId)
                .map(this::mapToResponse)
                .orElseThrow(() -> new RuntimeException("Activity not found with id: " + activityId));
    }
}