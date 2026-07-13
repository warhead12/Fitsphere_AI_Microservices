package com.fitness.userservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

// @SpringBootTest — Loads the full Spring Boot application context for integration testing.
// It starts the embedded server (if any) and wires all beans so that tests can verify
// the application starts without errors.
@SpringBootTest
class UserserviceApplicationTests {

	// @Test — Marks this method as a JUnit 5 test case.
	// contextLoads() is a basic smoke test: if the Spring context fails to start
	// (e.g., due to a missing bean or misconfigured property), this test will fail.
	@Test
	void contextLoads() {
	}

}
