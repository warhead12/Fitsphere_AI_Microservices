package com.fitness.userservice.dto;

import lombok.Data;

import java.time.LocalDateTime;

// @Data from Lombok generates getters, setters, toString, equals, hashCode — all the boilerplate.
// This DTO is the response object returned to clients after a user is created or retrieved.
// It hides the internal entity structure from the API consumer.
@Data
public class UserResponse {
    // The database-generated UUID of the user.
    private String id;
    // Keycloak federation ID (if the user was created via Keycloak SSO).
    private String keycloakId;
    // The user's email address (used as the login identifier).
    private String email;
    // The hashed/encoded password (never transmitted in plaintext in production).
    private String password;
    // User's given (first) name.
    private String firstName;
    // User's family (last) name.
    private String lastName;
    // Timestamp of when the user record was first created.
    private LocalDateTime createdAt;
    // Timestamp of the most recent update to the user record.
    private LocalDateTime updatedAt;
}