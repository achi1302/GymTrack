package com.example.gymtrack.ui.workouts.n3w

interface WorkoutsNewContract {

    interface View {
        fun onExerciseAdded()
        fun onWorkoutAdded()
        fun onError(message: String)
    }

    interface Presenter {
        fun submitWorkout(workoutName: String, category: String, description: String)
        fun addExercise(name: String, reps: Int, sets: Int, weight: Int)
    }
}