package com.example.gymtrack.data.model

import com.google.firebase.firestore.DocumentReference

data class Users (
    val user_name: String = "",
    val user_lastname: String = "",
    val username: String = "",
    val user_workouts: List<DocumentReference> = mutableListOf()
)