package com.example.gymtrack.data.model

import com.google.firebase.firestore.DocumentReference
import java.io.Serializable

data class WorkoutExercises(
    val exercise_id: String = "",
    val exercise_name: String = "",
    val reps: Int = 0,
    val sets: Int = 0,
    val weight: Int = 0
) : Serializable