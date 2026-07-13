package com.fitness.userservice.controller;

import com.fitness.userservice.dto.RegisterRequest;
import com.fitness.userservice.dto.UserResponse;
import com.fitness.userservice.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// @RestController marks this class as a Spring MVC controller where every method returns a domain object
// instead of a view — the response body is serialised directly (typically to JSON).
// @RequestMapping("/api/users") maps all HTTP requests to the "/api/users" prefix to this controller.
// @AllArgsConstructor from Lombok generates a constructor with one parameter for each final field,
// enabling Spring to inject the UserService dependency via constructor injection.
@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {

    // Injected service bean that contains the business logic for user operations.
    private UserService userService;

    // @GetMapping("/{userId}") — handles GET requests to "/api/users/{userId}". The {userId} placeholder
    // is extracted from the URL path via @PathVariable.
    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> getUserProfile(@PathVariable String userId){
        // Retrieves the user profile for the given userId and wraps it in a 200 OK response.
        return ResponseEntity.ok(userService.getUserProfile(userId));
    }

    // @PostMapping("/register") — handles POST requests to "/api/users/register".
    // @Valid triggers Jakarta Bean Validation on the request body — if validation fails,
    // Spring returns a 400 Bad Request automatically.
    // @RequestBody deserialises the JSON request body into a RegisterRequest DTO.
    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@Valid @RequestBody RegisterRequest request){
        // Registers a new user from the validated request data and returns the created user profile.
        return ResponseEntity.ok(userService.register(request));
    }


    // @GetMapping("/{userId}/validate") — handles GET requests to "/api/users/{userId}/validate".
    // The {userId} path variable is captured by @PathVariable.
    @GetMapping("/{userId}/validate")
    public ResponseEntity<Boolean> validateUser(@PathVariable String userId){
        // Checks whether a user with the given userId exists in the database and returns a boolean result.
        return ResponseEntity.ok(userService.existByUserId(userId));
    }
}