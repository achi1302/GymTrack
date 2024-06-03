package com.example.gymtrack.ui.Workouts.start

import com.example.gymtrack.data.model.WorkoutExercises
import com.google.firebase.firestore.DocumentReference


interface WorkoutsStartContract {
    interface View {
        fun showExercises(exercises: List<WorkoutExercises>)
    }

    interface Presenter {
        fun loadExercises()
    }
}