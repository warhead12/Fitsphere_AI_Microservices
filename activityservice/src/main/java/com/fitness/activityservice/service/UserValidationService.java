// UserValidationService.java
// Service that validates user existence by calling an external User Service via HTTP.
// Uses WebClient (reactive) to make the call and blocks for the response.
package com.fitness.activityservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@Service // = "Spring manages this as a service bean; it is injected into ActivityService for user pre-validation"
@Slf4j // = "Lombok provides a slf4j log field for logging"
@RequiredArgsConstructor // = "Lombok generates a constructor for the final WebClient field; Spring injects the WebClient bean"
public class UserValidationService {
    // Pre-configured WebClient pointing to the USER-SERVICE microservice (see WebClientConfig).
    private final WebClient userServiceWebClient;

    // Calls the User Service validation endpoint and returns true if the user exists, false otherwise.
    public boolean validateUser(String userId) {
        log.info("Calling User Validation API for userId: {}", userId);
        try{
            // Build a GET request to /api/users/{userId}/validate, retrieve the response, and block (synchronous) for the result.
            // Boolean.TRUE.equals() handles the case where the response body might be null or a non-boolean value.
            return Boolean.TRUE.equals(userServiceWebClient.get()
                    .uri("/api/users/{userId}/validate", userId)
                    .retrieve()
                    .bodyToMono(Boolean.class)
                    .block());
        } catch (WebClientResponseException e) {
            // Handle specific HTTP error codes from the User Service.
            if (e.getStatusCode() == HttpStatus.NOT_FOUND)
                throw new RuntimeException("User Not Found: " + userId);
            else if (e.getStatusCode() == HttpStatus.BAD_REQUEST)
                throw new RuntimeException("Invalid Request: " + userId);
        }
        // Fallback: return false if the user cannot be validated for any other reason.
        return false;
    }
}