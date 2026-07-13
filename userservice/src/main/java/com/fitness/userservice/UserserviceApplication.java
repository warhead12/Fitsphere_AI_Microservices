package com.fitness.userservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// @SpringBootApplication is a convenience annotation that combines @Configuration, @EnableAutoConfiguration,
// and @ComponentScan — it tells Spring Boot to auto-configure the application and scan for beans in this
// package and its sub-packages.
@SpringBootApplication
public class UserserviceApplication {

	// The main method is the standard Java entry point for the JVM. It delegates to SpringApplication#run,
	// which bootstraps the Spring application context and starts the embedded web server.
	public static void main(String[] args) {
		// SpringApplication.run() launches the entire Spring Boot application — it creates the
		// ApplicationContext, registers all beans, and starts the embedded Tomcat or Jetty server.
		SpringApplication.run(UserserviceApplication.class, args);
	}

}
