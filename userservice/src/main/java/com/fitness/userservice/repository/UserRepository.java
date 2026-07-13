package com.fitness.userservice.repository;

import com.fitness.userservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// @Repository — Marks this interface as a Spring Data repository. Spring automatically implements
// it at runtime by providing a proxy that handles all CRUD operations via JPA.
// Extending JpaRepository<User, String> gives us built-in methods like save(), findById(),
// existsById(), findAll(), and delete() — all with no boilerplate.
@Repository
public interface UserRepository extends JpaRepository<User, String> {

    // existsByEmail — Spring Data JPA parses the method name to derive the query.
    // It generates: SELECT COUNT(*) FROM users WHERE email = ? — returns true if a row exists.
    boolean existsByEmail(String email);

    // existsByKeycloakId — Same pattern as above; checks if a user exists with the given Keycloak ID.
    Boolean existsByKeycloakId(String userId);

    // findByEmail — Returns the first User whose email matches the given value.
    // Spring Data JPA generates: SELECT * FROM users WHERE email = ? LIMIT 1.
    User findByEmail(String email);
}