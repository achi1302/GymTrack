package com.example.gymtrack.data.model

import java.io.Serializable

data class Workouts(
    val id: String = "",
    val workout_name: String = "",
    val workout_category: String = "",
    val workout_description: String = "",
    val workout_exercises: List<WorkoutExercises> = mutableListOf()
    ) : Serializable