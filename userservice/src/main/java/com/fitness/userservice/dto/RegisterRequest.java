package com.fitness.userservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

// @Data from Lombok generates getters, setters, toString, equals, and hashCode for all fields.
// This DTO (Data Transfer Object) carries the incoming HTTP request body for user registration.
@Data
public class RegisterRequest {

    // @NotBlank — The string must not be null and must contain at least one non-whitespace character.
    // Validates that the email field is present in the request.
    @NotBlank(message = "Email is required")
    // @Email — Validates that the annotated string conforms to a basic email format (e.g. user@domain.com).
    @Email(message = "Invalid email format")
    private String email;

    // @NotBlank — Ensures the password field is not empty or null.
    @NotBlank(message = "Password is required")
    // @Size(min = 6) — The password string must have at least 6 characters.
    // This enforces a minimum length for basic security requirements.
    @Size(min = 6, message = "Password must have atleast 6 characters")
    private String password;

    // Optional field: if the user was already created in Keycloak, this field links the two identities.
    private String keycloakId;

    // Optional display name fields — not validated with @NotBlank so they may be omitted.
    private String firstName;
    private String lastName;

}