package com.example.gymtrack.ui.workouts

import com.example.gymtrack.data.model.Workouts

interface WorkoutsContract {
    interface View {
//        fun displayUserDetails(user: Users)
        fun displayWorkouts(workouts: List<Workouts>)
        fun navigateToExercises(workout: Workouts)
//        fun displayError(message: String)
    }

    interface Presenter {
        fun onViewCreated()
        fun onWorkoutClicked(selectedWorkout: Workouts)
    }
}