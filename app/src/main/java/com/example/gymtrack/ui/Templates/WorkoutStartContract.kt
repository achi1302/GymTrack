package com.example.gymtrack.ui.Templates

import com.example.gymtrack.data.model.Workout
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot

interface WorkoutStartContract {
    interface View {
        fun updateWorkoutItemList(workoutItems: List<Workout.ExerciseData>)
    }

    interface Presenter {
        fun fetchWorkoutData()
    }
}