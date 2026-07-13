// ActivityType.java
// Enumeration of all supported fitness activity types in the system.
// Used by Activity and ActivityRequest/ActivityResponse to categorize the type of workout.
package com.fitness.activityservice.model;

public enum ActivityType {
    RUNNING,
    WALKING,
    CYCLING,
    SWIMMING,
    WEIGHT_TRAINING,
    YOGA,
    HIT,     // High-Intensity Interval Training
    CARDIO,  // Cardiovascular exercise (e.g., elliptical, aerobics)
    STRETCHING,
    OTHER    // Catch-all for any activity type not explicitly listed above
}