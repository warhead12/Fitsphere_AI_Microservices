// ActivityRequest.java
// DTO for incoming activity tracking requests from the API client.
// Carries all the data needed to create a new activity record.
package com.fitness.activityservice.dto;

import com.fitness.activityservice.model.ActivityType;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

@Data // = "Lombok generates getters, setters, toString, equals, and hashCode — allows Jackson to deserialize/serialize this DTO automatically"
public class ActivityRequest {
    private String userId;
    private ActivityType type;
    private Integer duration;
    private Integer caloriesBurned;
    private LocalDateTime startTime;
    private Map<String, Object> additionalMetrics;
}