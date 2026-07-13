// Activity.java
// MongoDB document model representing a user's fitness activity.
// Each document is stored in the 'activities' collection and contains all data about a single tracked activity.
package com.fitness.activityservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.Map;

@Document(collection = "activities") // = "Maps this class to the 'activities' collection in MongoDB — instances are stored as documents in this collection"
@Data // = "Lombok generates getters, setters, equals, hashCode, toString for all fields"
@Builder // = "Lombok provides the builder pattern for constructing Activity instances with a fluent API"
@AllArgsConstructor // = "Lombok generates a constructor with all fields"
@NoArgsConstructor // = "Lombok generates a no-argument constructor (required by MongoDB for deserialization)"
public class Activity {
    @Id // = "Marks this field as the MongoDB document _id; MongoDB auto-generates it if not set"
    private String id;
    private String userId;
    private ActivityType type;
    private Integer duration;
    private Integer caloriesBurned;
    private LocalDateTime startTime;

    @Field("metrics") // = "Custom field name mapping: this field is stored as 'metrics' in MongoDB (not 'additionalMetrics') — overrides the default Java field name"
    private Map<String, Object> additionalMetrics;

    @CreatedDate // = "Spring Data MongoDB automatically sets this field to the current timestamp when the document is first created"
    private LocalDateTime createdAt;

    @LastModifiedDate // = "Spring Data MongoDB automatically updates this field to the current timestamp every time the document is modified"
    private LocalDateTime updatedAt;
}