package com.example.gymtrack.ui.workouts.start

import com.example.gymtrack.data.model.Workouts

class WorkoutsStartPresenter(private val view: WorkoutsStartContract.View,
                             private val selectedWorkout: Workouts
): WorkoutsStartContract.Presenter {

    override fun onViewCreated() {
        view.displayExercises(selectedWorkout.workout_exercises)
    }
}