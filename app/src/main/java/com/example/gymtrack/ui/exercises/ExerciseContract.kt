package com.example.gymtrack.ui.exercises

import com.example.gymtrack.data.model.Exercise

interface ExerciseContract {
    interface View {
        fun showExercise(exercise: List<Exercise>?)
        fun showDialogInfo(exercise: Exercise) {}
    }

    interface Presenter {
        fun getExercise(muscle:String)

        fun triggerDialogInfo(exercise: Exercise)
    }
}