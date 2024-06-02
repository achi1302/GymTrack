package com.example.gymtrack.data.model

import com.google.firebase.firestore.DocumentReference

data class Users (
    val user_lastname: String,
    val user_name: String,
    val user_workouts: List<DocumentReference>,
    val username: String
)