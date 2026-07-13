// AiserviceApplication.java
// Main entry point for the AI Service microservice.
// This is a Spring Boot application that generates activity-based recommendations (e.g., safety tips, improvement suggestions) for users.
package com.fitness.aiservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // = "Declares this class as the Spring Boot auto-configuration entry point — enables auto-configuration, component scanning, and property support"
public class AiserviceApplication {

	public static void main(String[] args) {
		// Launch the Spring Boot application — boots the embedded server, loads all beans, and starts the AI service.
		SpringApplication.run(AiserviceApplication.class, args);
	}

}
