// Activity.java
// Plain DTO/POJO in the AI Service representing an activity forwarded from the Activity Service via RabbitMQ.
// This is a lightweight copy of the Activity model — used only for message deserialization, not for persistence in this service.
package com.fitness.aiservice.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

@Data // = "Lombok generates getters, setters, equals, hashCode, and toString for all fields"
public class Activity {

    private String id;
    private String userId;
    private String type;
    private Integer duration;
    private Integer caloriesBurned;
    private LocalDateTime startTime;

    private Map<String, Object> additionalMetrics;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}