// ActivityResponse.java
// DTO (Data Transfer Object) returned by the API to clients when querying or tracking activities.
// Separates the internal persistence model from the external API contract.
package com.fitness.activityservice.dto;

import com.fitness.activityservice.model.ActivityType;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

@Data // = "Lombok generates all getters, setters, toString, equals, and hashCode for all fields — keeps the DTO clean and boilerplate-free"
public class ActivityResponse {
    private String id;
    private String userId;
    private ActivityType type;
    private Integer duration;
    private Integer caloriesBurned;
    private LocalDateTime startTime;
    private Map<String, Object> additionalMetrics;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}