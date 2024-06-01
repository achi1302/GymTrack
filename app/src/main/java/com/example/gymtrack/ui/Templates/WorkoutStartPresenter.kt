package com.example.gymtrack.ui.Templates

import android.util.Log
import com.example.gymtrack.data.model.Workout
import com.google.android.gms.tasks.Tasks
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore

class WorkoutStartPresenter(
    private val db: FirebaseFirestore,
    private val view: WorkoutStartContract.View
) : WorkoutStartContract.Presenter {

    override fun fetchWorkoutData() {
        db.collection("Workouts").document("p7YaNnAU6Az5Wmb9cbsj")
            .get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    val workout = document.toObject(Workout::class.java)
                    if (workout != null) {
                        val tasks = workout.workout_exercises.map { exerciseData ->
                            db.collection("Exercises").document(exerciseData.exercise_id).get()
                                .continueWith { task ->
                                    if (task.isSuccessful) {
                                        val exerciseDocument = task.result
                                        val exerciseName = exerciseDocument?.getString("exercise_name") ?: ""
                                        Workout.ExerciseData(
                                            exerciseName,
                                            exerciseData.sets,
                                            exerciseData.weight,
                                            exerciseData.reps
                                        )
                                    } else null
                                }
                        }

                        Tasks.whenAllSuccess<Workout.ExerciseData>(tasks).addOnSuccessListener { workoutItems ->
                            view.updateWorkoutItemList(workoutItems)
                        }
                    }
                }
            }
            .addOnFailureListener { exception ->
                Log.d("Workout", "get failed with ", exception)
            }
    }
}