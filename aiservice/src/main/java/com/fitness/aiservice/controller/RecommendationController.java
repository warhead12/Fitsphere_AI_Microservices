// RecommendationController.java
// REST controller exposing HTTP endpoints for the AI Recommendation Service.
// Provides endpoints to retrieve recommendations for a specific user or a specific activity.
package com.fitness.aiservice.controller;

import com.fitness.aiservice.model.Recommendation;
import com.fitness.aiservice.service.RecommendationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController // = "Marks this class as a REST controller — its methods return data (JSON) directly in the HTTP response body"
@RequiredArgsConstructor // = "Lombok generates a constructor for the final field (recommendationService); Spring uses it for dependency injection"
@RequestMapping("/api/recommendations") // = "Base URL path for all recommendation-related endpoints in this controller"
public class RecommendationController {
    // Injected service bean that contains the business logic for retrieving recommendations.
    private final RecommendationService recommendationService;

    @GetMapping("/user/{userId}") // = "Maps HTTP GET requests to retrieve all recommendations for a specific user"
    public ResponseEntity<List<Recommendation>> getUserRecommendation(@PathVariable String userId) {
        // Fetch the list of recommendations for the given user from the service layer.
        return ResponseEntity.ok(recommendationService.getUserRecommendation(userId));
    }

    @GetMapping("/activity/{activityId}") // = "Maps HTTP GET requests to retrieve the recommendation for a specific activity"
    public ResponseEntity<Recommendation> getActivityRecommendation(@PathVariable String activityId) {
        // Fetch a single recommendation by activity ID; throws 404 if not found.
        return ResponseEntity.ok(recommendationService.getActivityRecommendation(activityId));
    }
}