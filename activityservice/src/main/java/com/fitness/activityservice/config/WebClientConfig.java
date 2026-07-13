// WebClientConfig.java
// Configuration for creating a WebClient instance that communicates with the USER-SERVICE microservice.
// Uses Spring Cloud LoadBalancer for service discovery resolution within a Kubernetes/Docker Compose environment.
package com.fitness.activityservice.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration // = "Declares this class as a Spring configuration class — its @Bean methods are processed by Spring"
public class WebClientConfig {

    @Bean // = "Creates a WebClient.Builder annotated with @LoadBalanced — this enables service discovery (resolves http://USER-SERVICE to actual instances)"
    @LoadBalanced
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }

    @Bean // = "Builds a concrete WebClient that points to the USER-SERVICE microservice using load-balanced service discovery"
    // The method parameter (WebClient.Builder) is auto-injected by Spring; the WebClient is built with a base URL that will be resolved via service discovery.
    public WebClient userServiceWebClient(WebClient.Builder webClientBuilder) {
        return webClientBuilder
                .baseUrl("http://USER-SERVICE") // "USER-SERVICE" is the logical service name registered in the service registry (e.g., Eureka or Kubernetes)
                .build();
    }
}