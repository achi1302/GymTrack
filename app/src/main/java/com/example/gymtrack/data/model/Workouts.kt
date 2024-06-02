package com.example.gymtrack.data.model

data class Workouts(
    val workout_category: String,
    val workout_exercises: List<WorkoutsExercise>,
    val workout_name: String
)