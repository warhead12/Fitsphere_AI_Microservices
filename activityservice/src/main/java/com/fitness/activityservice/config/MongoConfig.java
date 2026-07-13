// MongoConfig.java
// Spring Data MongoDB configuration that enables automatic timestamp auditing for all MongoDB documents.
// @CreatedDate, @LastModifiedDate annotations on entity fields rely on this.
package com.fitness.activityservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@Configuration // = "Marks this as a Spring configuration class — it is scanned and processed during application startup"
@EnableMongoAuditing // = "Activates automatic auditing for MongoDB documents; Spring sets @CreatedDate and @LastModifiedDate fields automatically on save/update"
public class MongoConfig {
}