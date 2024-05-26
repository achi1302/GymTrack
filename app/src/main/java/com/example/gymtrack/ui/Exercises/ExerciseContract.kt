package com.example.gymtrack.ui.Exercises

import com.example.gymtrack.data.model.Exercise

interface ExerciseContract {
    interface View {
        fun showExercise(exercise: List<Exercise>?)
    }

    interface Presenter {
        fun getExercise(muscle:String)
    }
}