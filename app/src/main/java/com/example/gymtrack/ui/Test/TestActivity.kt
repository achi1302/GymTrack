package com.example.gymtrack.ui.Test

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gymtrack.R
import com.example.gymtrack.data.model.Users
import com.example.gymtrack.data.model.Workouts
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class TestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        val db = Firebase.firestore
        val userName = findViewById<TextView>(R.id.user_name_tv)
        val userLastname = findViewById<TextView>(R.id.user_lastname_tv)
        val usernameTV = findViewById<TextView>(R.id.username_tv)

        val workouts = mutableListOf<Workouts>()
        val workoutAdapter = TestAdapter(workouts) { selectedWorkout ->
            // Start TestExercisesActivity with selected workout data
            val intent = Intent(this, TestExercisesActivity::class.java)
            // Pass workout data as serializable object
            intent.putExtra("selected_workout", selectedWorkout)
            startActivity(intent)
        }

        val recyclerView = findViewById<RecyclerView>(R.id.test_rv)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = workoutAdapter // Setting adapter using the instance

        val docRef = db.collection("Users").document("FWAzJkVoLpRsApIk3PPY")
        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    val user = document.toObject(Users::class.java)

                    userName.text = user?.user_name
                    userLastname.text = user?.user_lastname
                    usernameTV.text = user?.username

                    for (workoutRef in user?.user_workouts ?: emptyList()) {
                        workoutRef.get().addOnSuccessListener { workoutDocument ->
                            val workout = workoutDocument.toObject(Workouts::class.java)?.copy(id = workoutDocument.id)
                            if (workout != null) {
                                workouts.add(workout)
                                workoutAdapter.notifyItemInserted(workouts.size - 1)
                            }
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