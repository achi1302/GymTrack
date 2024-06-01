package com.example.gymtrack.data.model

data class Workout(
    val id: String = "",
    val workout_category: String = "",
    val workout_exercises: List<ExerciseData> = listOf(),
    val workout_name: String = ""
) {
    data class ExerciseData(
        val exercise_id: String = "",
        val reps: Int = 0,
        val sets: Int = 0,
        val weight: Int = 0
    )
}