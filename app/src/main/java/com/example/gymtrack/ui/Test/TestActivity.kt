package com.example.gymtrack.ui.Test

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.gymtrack.R
import com.example.gymtrack.data.model.Users
import com.example.gymtrack.data.model.Workouts
import com.google.firebase.Firebase
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject

class TestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        val db = Firebase.firestore
        val userName = findViewById<TextView>(R.id.user_name_tv)
        val userLastname = findViewById<TextView>(R.id.user_lastname_tv)
        val username = findViewById<TextView>(R.id.username_tv)
        val userWorkouts = findViewById<TextView>(R.id.user_workouts_tv)
        val exerciseSets = findViewById<TextView>(R.id.exercise_sets_tv)
        val exerciseReps = findViewById<TextView>(R.id.exercise_reps_tv)
        val exerciseWeight = findViewById<TextView>(R.id.exercise_weight_tv)

        val docRef = db.collection("Users").document("FWAzJkVoLpRsApIk3PPY")
        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    Log.d("exists", "DocumentSnapshot data: ${document.data}")

                    val user = document.toObject(Users::class.java)

                    userName.text = user?.user_name
                    userLastname.text = user?.user_lastname
                    username.text = user?.username

                    // Create a list to store workout names
                    val workoutNames = mutableListOf<String>()

                    // Loop through each reference
                    for (workoutRef in user?.user_workouts ?: emptyList()) {
                        workoutRef.get().addOnSuccessListener { workoutDocument ->
                            val workout = workoutDocument.toObject(Workouts::class.java)
                            val workoutName = workout?.workout_name
                            if (workoutName != null) {
                                workoutNames.add(workoutName)
                            }
                            // Loop through each Exercise in the current Workout
                            for (exercise in workout?.workout_exercises ?: emptyList()) {
                                // Here, exercise is an object of WorkoutExercise
                                exerciseSets.text = exercise.sets.toString()
                                exerciseReps.text = exercise.reps.toString()
                                exerciseWeight.text = exercise.weight.toString()
                            }
                            Log.d("Workouts", workoutNames.toString())
                            userWorkouts.text = workoutNames.joinToString(separator = "\n") //Convert workout names to string for diplay
                        }.addOnFailureListener { exception ->
                            Log.d("Error", "get failed with ", exception)
                        }
                    }

                } else {
                    Log.d("noexists", "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.d("error", "get failed with ", exception)
            }

    }
}