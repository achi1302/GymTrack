package com.example.gymtrack.data.model

import com.google.firebase.firestore.DocumentReference

data class WorkoutExercises(
    val exercise_id: DocumentReference? = null,
    val reps: Int = 0,
    val sets: Int = 0,
    val weight: Int = 0
)