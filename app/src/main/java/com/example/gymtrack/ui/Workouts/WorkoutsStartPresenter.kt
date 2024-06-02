package com.example.gymtrack.ui.Workouts

import android.util.Log
import com.example.gymtrack.data.model.Users
import com.example.gymtrack.data.model.WorkoutExercises
import com.example.gymtrack.data.model.Workouts
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class WorkoutsStartPresenter(private val view: WorkoutsStartContract.View): WorkoutsStartContract.Presenter {
    private val db = Firebase.firestore

    override fun loadExercises() {
        val docRef = db.collection("Users").document("FWAzJkVoLpRsApIk3PPY")
        docRef.get().addOnSuccessListener { document ->
            val user = document.toObject(Users::class.java)

            // The list that stored all exercises across workouts
            val exercisesAcrossWorkouts = mutableListOf<WorkoutExercises>()

            // If there are no workouts for the user, just show the exercises (empty list)
            val workouts = user?.user_workouts
            if (workouts == null || workouts.isEmpty()) {
                view.showExercises(exercisesAcrossWorkouts)
                return@addOnSuccessListener
            }

            // Count for remaining workouts to fetch exercises for
            var remainingWorkouts = workouts.size

            // Loop through each reference
            for (workoutRef in workouts) {
                workoutRef.get().addOnSuccessListener { workoutDocument ->
                    val workout = workoutDocument.toObject(Workouts::class.java)

                    // Add all exercises of this workout to the cumulative list
                    exercisesAcrossWorkouts.addAll(workout?.workout_exercises ?: emptyList())

                    remainingWorkouts--

                    // If there are no more workouts, display fetched exercises
                    if (remainingWorkouts == 0) {
                        view.showExercises(exercisesAcrossWorkouts)
                    }
                }.addOnFailureListener { exception ->
                    Log.d("Error", "get failed with ", exception)
                }
            }
        }.addOnFailureListener { exception ->
            Log.d("Error", "get failed with ", exception)
        }
    }
}




