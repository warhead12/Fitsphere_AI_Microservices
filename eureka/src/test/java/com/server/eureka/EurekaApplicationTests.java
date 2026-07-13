package com.server.eureka;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest  // <-- Loads the full Spring Boot application context for integration testing — simulates what happens when the app starts
class EurekaApplicationTests {

	@Test  // <-- Marks this method as a JUnit 5 test case
	void contextLoads() {
		// <-- Empty test: if the Spring context starts successfully, this test passes. It verifies all beans, configs, and dependencies can be wired without errors.
	}

}
