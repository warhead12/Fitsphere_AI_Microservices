// ActivityserviceApplication.java
// Main entry point for the Activity Service microservice.
// This is a Spring Boot application that manages user fitness activities (tracking, CRUD, event publishing).
package com.fitness.activityservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // = "Declares this class as the Spring Boot auto-configuration entry point; enables auto-configuration, component scanning, and property support"
public class ActivityserviceApplication {

	public static void main(String[] args) {
		// Launch the Spring Boot application — boots the embedded server, loads all beans, and starts the service.
		SpringApplication.run(ActivityserviceApplication.class, args);
	}

}
