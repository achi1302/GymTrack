package com.example.gymtrack.ui.Test

import com.example.gymtrack.data.model.Users
import com.example.gymtrack.data.model.Workouts
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class TestPresenter(private val view: TestContract.View) : TestContract.Presenter {
    private val model: TestModel = TestModel()

    override fun onViewCreated() {
        model.fetchUserData { user ->
            if (user != null) {
                view.displayUserDetails(user)
                model.fetchWorkouts(user) { workouts ->
                    view.displayWorkouts(workouts)
                }
            } else {
                view.displayError("Oops! There seems to be a problem with loading your data. Please try again later.")
            }
        }
    }

    override fun onWorkoutClicked(selectedWorkout: Workouts) {
        view.navigateToExercises(selectedWorkout)
    }

    class TestModel {
        private val db = Firebase.firestore

        fun fetchUserData(onUserDataFetched: (Users?) -> Unit) {
            db.collection("Users").document("FWAzJkVoLpRsApIk3PPY")
                .get().addOnSuccessListener { document ->
                    val user = document.toObject(Users::class.java)
                    onUserDataFetched(user)
                }
        }

        fun fetchWorkouts(user: Users, onWorkoutsFetched: (List<Workouts>) -> Unit) {
            val workouts = mutableListOf<Workouts>()
            user.user_workouts.forEach { workoutRef ->
                workoutRef.get().addOnSuccessListener { workoutDocument ->
                    val workout = workoutDocument.toObject(Workouts::class.java)
                    if (workout != null) {
                        workouts.add(workout)
                        if(workouts.size == user.user_workouts.size) {
                            onWorkoutsFetched(workouts)
                        }
                    }
                }
            }
        }
    }
}

