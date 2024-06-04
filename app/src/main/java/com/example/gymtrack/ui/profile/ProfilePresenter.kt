package com.example.gymtrack.ui.profile

import com.example.gymtrack.data.model.Users
import com.example.gymtrack.data.model.Workouts

import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class ProfilePresenter(private val view: ProfileContract.View): ProfileContract.Presenter {

    private val model: TestModel = TestModel()

    override fun onViewCreated() {
        model.fetchWorkouts(view) { workouts ->
            view.displayWorkouts(workouts)
        }
    }

    override fun onWorkoutClicked(selectedWorkout: Workouts) {
        view.navigateToExercises(selectedWorkout)
    }

    class TestModel {
        private val db = Firebase.firestore

        fun fetchWorkouts(view: ProfileContract.View, onWorkoutsFetched: (List<Workouts>) -> Unit) {
            db.collection("Users").document("FWAzJkVoLpRsApIk3PPY")
                .get().addOnSuccessListener { document ->
                    if (document != null) {
                        val user = document.toObject(Users::class.java)
                        if (user != null) {
                            view.displayUserDetails(user)
                        }
                        val workouts = mutableListOf<Workouts>()
                        user?.user_workouts?.forEach { workoutRef ->
                            workoutRef.get().addOnSuccessListener { workoutDocument ->
                                val workout = workoutDocument.toObject(Workouts::class.java)
                                if (workout != null) {
                                    workouts.add(workout)
                                    if (workouts.size == user.user_workouts.size) {
                                        onWorkoutsFetched(workouts)
                                    }
                                }
                            }
                        } ?: run {
                            onWorkoutsFetched(emptyList())
                        }
                    }
                }
        }
    }
}
