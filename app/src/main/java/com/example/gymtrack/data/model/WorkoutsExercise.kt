package com.example.gymtrack.data.model

import com.google.firebase.firestore.DocumentReference

data class WorkoutsExercise(
    val exercise_id: DocumentReference,
    val reps: Int,
    val sets: Int,
    val weight: Int
)