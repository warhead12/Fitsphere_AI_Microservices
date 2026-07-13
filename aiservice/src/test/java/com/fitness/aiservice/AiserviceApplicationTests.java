// AiserviceApplicationTests.java
// Integration test that verifies the Spring Boot application context for the AI Service starts up successfully.
// If the application context fails (e.g., missing beans, misconfigured properties), this test fails.
package com.fitness.aiservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest // = "Spring Boot test that loads the full application context for integration testing"
class AiserviceApplicationTests {

	@Test // = "JUnit 5 test method — verifies that the Spring application context initializes without errors"
	void contextLoads() {
		// Empty test body: @SpringBootTest alone verifies that all beans, configurations, and dependencies are correctly wired.
	}

}
