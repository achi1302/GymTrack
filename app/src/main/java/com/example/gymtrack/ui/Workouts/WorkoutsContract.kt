package com.example.gymtrack.ui.Workouts

import com.example.gymtrack.data.model.Workouts

interface WorkoutsContract {
    interface View {
        fun showWorkouts(workouts: List<Workouts>)
    }

    interface Presenter {
        fun loadWorkouts()
    }
}