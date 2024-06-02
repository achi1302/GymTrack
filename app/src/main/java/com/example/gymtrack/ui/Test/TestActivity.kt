package com.example.gymtrack.ui.Test

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.gymtrack.R
import com.google.firebase.Firebase
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.firestore

class TestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        val db = Firebase.firestore
        val user_name = findViewById<TextView>(R.id.user_name)
        val user_lastname = findViewById<TextView>(R.id.user_lastname)
        val username = findViewById<TextView>(R.id.username)
        val user_workouts = findViewById<TextView>(R.id.user_workouts)

        val docRef = db.collection("Users").document("FWAzJkVoLpRsApIk3PPY")
        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    Log.d("exists", "DocumentSnapshot data: ${document.data}")

                    user_name.text = document.getString("user_name")
                    user_lastname.text = document.getString("user_lastname")
                    username.text = document.getString("username")

                    // Get the user_workouts field which is a List
                    val workoutsRefs = document.get("user_workouts") as List<DocumentReference>
                    // Create a list to store workout names
                    val workoutNames = mutableListOf<String>()

                    // Loop through each reference
                    for (workoutRef in workoutsRefs) {
                        workoutRef.get().addOnSuccessListener { workoutDocument ->
                            val workoutName = workoutDocument.getString("workout_name")
                            if (workoutName != null) {
                                workoutNames.add(workoutName)
                            }
                            user_workouts.text = workoutNames.joinToString(separator = "\n") //Convert workout names to string for diplay
                            Log.d("Workouts", workoutNames.toString())
                        }.addOnFailureListener { exception ->
                            Log.d("Error", "get failed with ", exception)
                        }
                    }

                } else {
                    Log.d("noesxists", "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.d("error", "get failed with ", exception)
            }

    }
}