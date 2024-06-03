package com.example.gymtrack.ui.workouts.start

import com.example.gymtrack.data.model.WorkoutExercises


interface WorkoutsStartContract {
    interface View {
        fun displayExercises(exercises: List<WorkoutExercises>)
    }

    interface Presenter {
        fun onViewCreated()
    }
}