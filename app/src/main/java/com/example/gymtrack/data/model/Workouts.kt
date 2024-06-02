package com.example.gymtrack.data.model

data class Workouts(
    val workout_name: String = "",
    val workout_category: String = "",
    val workout_exercises: List<WorkoutExercises> = mutableListOf()
    )