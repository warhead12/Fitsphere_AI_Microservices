package com.fitness.userservice.model;

// UserRole is an enum representing the possible roles a user can hold in the system.
// - USER: Standard end-user with basic access privileges.
// - ADMIN: Administrator with elevated privileges (e.g., managing other users or system configuration).
// Values are stored as their string name in the database due to @Enumerated(EnumType.STRING).
public enum UserRole {
    USER, ADMIN
}