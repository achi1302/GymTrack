package com.example.gymtrack.ui.Test

import com.example.gymtrack.data.model.Users
import com.example.gymtrack.data.model.Workouts

interface TestContract {
    interface View {
        fun displayUserDetails(user: Users)
        fun displayWorkouts(workouts: List<Workouts>)
        fun navigateToExercises(workout: Workouts)
        fun displayError(message: String)
    }

    interface Presenter {
        fun onViewCreated()
        fun onWorkoutClicked(selectedWorkout: Workouts)
    }
}