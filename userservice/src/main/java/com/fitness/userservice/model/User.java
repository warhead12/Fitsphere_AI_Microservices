package com.fitness.userservice.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

// @Entity — Marks this class as a JPA entity, meaning its instances are mapped to rows in a database table.
// @Table(name = "users") — Maps this entity to the "users" table in the database.
// @Data — Lombok annotation that generates getters, setters, toString, equals, and hashCode.
@Entity
@Table(name = "users")
@Data
public class User {

    // @Id — Marks this field as the primary key of the entity.
    // @GeneratedValue(strategy = GenerationType.UUID) — The primary key is generated as a UUID string
    // by Hibernate, providing a globally unique identifier.
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String keycloakId;
    // @Column(unique = true, nullable = false) — The "email" column has a UNIQUE constraint and
    // cannot be null. This prevents duplicate email registrations at the database level.
    @Column(unique = true, nullable = false)
    private String email;

    // Keycloak federation ID — optional; links this DB record to an external identity provider.


    // @Column(nullable = false) — The password field is required and cannot be null.
    @Column(nullable = false)
    private String password;

    // User's first name (optional, no column constraints).
    private String firstName;
    // User's last name (optional, no column constraints).
    private String lastName;

    // @Enumerated(EnumType.STRING) — The UserRole enum is stored as a plain string in the database
    // (e.g., "USER" or "ADMIN") rather than an ordinal number — this makes it safe to reorder enum values.
    @Enumerated(EnumType.STRING)
    // Default role assigned to every newly registered user.
    private UserRole role = UserRole.USER;

    // @CreationTimestamp — Hibernate automatically sets this field to the current timestamp
    // when the entity is first persisted. It is never updated afterwards.
    @CreationTimestamp
    private LocalDateTime createdAt;

    // @UpdateTimestamp — Hibernate automatically updates this field to the current timestamp
    // every time the entity is modified and persisted.
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}