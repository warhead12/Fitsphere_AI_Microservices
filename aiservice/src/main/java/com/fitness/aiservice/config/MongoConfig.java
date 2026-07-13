// MongoConfig.java
// Spring Data MongoDB configuration that enables automatic timestamp auditing for all AI Service MongoDB documents.
// @CreatedDate annotations on recommendation model fields rely on this.
package com.fitness.aiservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@Configuration // = "Marks this as a Spring configuration class — it is scanned and processed during application startup"
@EnableMongoAuditing // = "Activates automatic auditing for MongoDB documents; Spring sets @CreatedDate fields automatically on save"
public class MongoConfig {
}