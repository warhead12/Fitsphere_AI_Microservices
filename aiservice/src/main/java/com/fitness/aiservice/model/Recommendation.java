// Recommendation.java
// MongoDB document model representing an AI-generated recommendation for a fitness activity.
// Each document is stored in the 'recommendations' collection and contains safety tips, improvements, and suggestions.
package com.fitness.aiservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "recommendations") // = "Maps this class to the 'recommendations' collection in MongoDB — instances are stored as documents"
@Data // = "Lombok generates getters, setters, equals, hashCode, and toString"
@Builder // = "Lombok provides the builder pattern for constructing Recommendation instances with a fluent API"
@AllArgsConstructor // = "Lombok generates a constructor with all fields"
@NoArgsConstructor // = "Lombok generates a no-argument constructor (required by MongoDB for deserialization)"
public class Recommendation {
    @Id // = "Marks this field as the MongoDB document _id; auto-generated if not set"
    private String id;
    private String activityId;
    private String userId;
    private String activityType;
    private String recommendation;
    private List<String> improvements;
    private List<String> suggestions;
    private List<String> safety;

    @CreatedDate // = "Spring Data MongoDB automatically sets this field to the current timestamp when the document is first created"
    private LocalDateTime createdAt;
}