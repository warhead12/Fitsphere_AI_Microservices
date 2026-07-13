// ActivityRepository.java
// Spring Data MongoDB repository interface for the Activity document.
// Provides built-in CRUD operations and a custom query method for looking up activities by user ID.
package com.fitness.activityservice;

import com.fitness.activityservice.model.Activity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository // = "Marks this interface as a Spring Data repository; Spring automatically generates the implementation at runtime"
public interface ActivityRepository extends MongoRepository<Activity, String> {
    // Custom query method: retrieves all activity records that belong to a specific user.
    // Spring Data MongoDB parses the method name 'findByUserId' to build the query automatically.
    List<Activity> findByUserId(String userId);
}