package com.example.gymtrack.ui.workouts.n3w

import com.example.gymtrack.data.model.WorkoutExercises
import com.example.gymtrack.data.model.Workouts
import com.google.firebase.Firebase
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.firestore
import java.util.UUID

class WorkoutsNewPresenter (val view: WorkoutsNewContract.View) : WorkoutsNewContract.Presenter {

    private val db = Firebase.firestore
    private val workoutExercisesList = mutableListOf<WorkoutExercises>()

    override fun submitWorkout(workoutName: String, category: String, description: String) {
        val workoutId = UUID.randomUUID().toString()

        val workout = Workouts(
            workoutId,
            workoutName,
            category,
            description,
            workoutExercisesList
        )

        val workoutsCollection = db.collection("Workouts")
        val documentReference = workoutsCollection.document(workoutId)

        documentReference.set(workout)
            .addOnSuccessListener {
                // Path of the user document
                val userDocumentPath = "Users/FWAzJkVoLpRsApIk3PPY"
                val userDocumentReference = db.document(userDocumentPath)

                // Update user document: add workout document reference to the user_workouts list
                userDocumentReference.update("user_workouts", FieldValue.arrayUnion(documentReference))
                    .addOnSuccessListener {
                        view.onWorkoutAdded()
                    }
                    .addOnFailureListener { e ->
                        view.onError("Error adding Workout Reference to the User: $e")
                    }

                // Clear list after successful submission
                workoutExercisesList.clear()
            }
            .addOnFailureListener { e ->
                view.onError("Error writing Workout to Firestore: $e")
            }

    }

    override fun addExercise(name: String, reps: Int, sets: Int, weight: Int) {
        val exerciseId = UUID.randomUUID().toString()
        val newWorkoutExercise = WorkoutExercises(exerciseId, name, reps, sets, weight)

        workoutExercisesList.add(newWorkoutExercise)

        view.onExerciseAdded()
    }
}