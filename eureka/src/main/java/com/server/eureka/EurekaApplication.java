package com.server.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication  // <-- Tells Spring Boot: "enable auto-configuration, component scanning, and property loading" — this is the entry point for the entire app
@EnableEurekaServer     // <-- Marks this service as a Eureka Service Registry — other microservices register here so they can discover each other by name instead of hard-coding IP addresses
public class EurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaApplication.class, args);  // <-- Bootstraps the app: creates Spring context, starts embedded Tomcat, and launches the Eureka server on port 8761
	}

}