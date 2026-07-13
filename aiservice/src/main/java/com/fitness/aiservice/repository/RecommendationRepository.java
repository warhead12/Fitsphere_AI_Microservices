// RecommendationRepository.java
// Spring Data MongoDB repository interface for the Recommendation document.
// Provides built-in CRUD operations and custom query methods to look up recommendations by user or activity.
package com.fitness.aiservice.repository;

import com.fitness.aiservice.model.Recommendation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository // = "Marks this interface as a Spring Data repository; Spring automatically generates the implementation at runtime"
public interface RecommendationRepository extends MongoRepository<Recommendation, String> {
    // Custom query: returns all recommendation documents associated with a given user ID.
    List<Recommendation> findByUserId(String userId);
    // Custom query: returns the recommendation for a specific activity by its ID — returns Optional since there may be one or zero recommendations per activity.
    Optional<Recommendation> findByActivityId(String activityId);
}