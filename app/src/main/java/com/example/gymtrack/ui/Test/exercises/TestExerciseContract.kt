package com.example.gymtrack.ui.Test.exercises

import com.example.gymtrack.data.model.WorkoutExercises


interface TestExerciseContract {
    interface View {
        fun displayExercises(exercises: List<WorkoutExercises>)
    }

    interface Presenter {
        fun onViewCreated()
    }
}