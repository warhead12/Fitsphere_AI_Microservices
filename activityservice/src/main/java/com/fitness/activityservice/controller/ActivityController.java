// ActivityController.java
// REST controller exposing HTTP endpoints for the Activity Service.
// Handles activity tracking (POST) and activity retrieval (GET) requests.
package com.fitness.activityservice.controller;

import com.fitness.activityservice.dto.ActivityRequest;
import com.fitness.activityservice.dto.ActivityResponse;
import com.fitness.activityservice.service.ActivityService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // = "Marks this class as a REST controller; every method returns data (not a view) — responses are serialized to JSON automatically"
@RequestMapping("/api/activities") // = "Maps all HTTP requests matching this path pattern to this controller; base URL for every endpoint in this class"
@AllArgsConstructor // = "Lombok generates a constructor with one parameter for every field (activityService) — Spring injects the bean via this constructor"
public class ActivityController {

    // Injected service bean that contains the business logic for activity operations.
    private ActivityService activityService;

    @PostMapping // = "Maps HTTP POST requests (create/new activity) to this method; the request body is deserialized from JSON"
    public ResponseEntity<ActivityResponse> trackActivity(@RequestBody ActivityRequest request, @RequestHeader("X-User-ID") String userId){
        // If the X-User-ID header is present, override the userId in the request object.
        // This ensures the caller's identity is always set from the authenticated header, not from the request body.
        if (userId != null) {
            request.setUserId(userId);
        }
        // Delegate to the service layer for processing; wrap the result in HTTP 200 OK.
        return ResponseEntity.ok(activityService.trackActivity(request));
    }

    @GetMapping // = "Maps HTTP GET requests (retrieve all activities for a user) to this method"
    public ResponseEntity<List<ActivityResponse>> getUserActivities(@RequestHeader("X-User-ID") String userId){
        // Fetch all activity records for the given user from the database and return them in the HTTP response.
        return ResponseEntity.ok(activityService.getUserActivities(userId));
    }


    @GetMapping("/{activityId}") // = "Maps HTTP GET requests with a specific activity ID in the path to this method"
    public ResponseEntity<ActivityResponse> getActivity(@PathVariable String activityId){ // @PathVariable extracts the {activityId} segment from the URL
        // Fetch a single activity by its unique ID and return it in the HTTP response.
        return ResponseEntity.ok(activityService.getActivityById(activityId));
    }
}