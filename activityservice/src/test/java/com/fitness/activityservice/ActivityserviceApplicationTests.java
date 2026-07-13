// ActivityserviceApplicationTests.java
// Integration test that verifies the Spring Boot application context starts up successfully.
// If the application context fails to load (e.g., missing beans, misconfigured properties), this test fails.
package com.fitness.activityservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest // = "Spring Boot test that loads the full application context for integration testing"
class ActivityserviceApplicationTests {

	@Test // = "JUnit 5 test method — this test verifies that the Spring application context initializes without errors"
	void contextLoads() {
		// Empty test body: Spring Boot's @SpringBootTest annotation alone verifies that all beans, configurations, and dependencies are correctly wired.
	}

}
