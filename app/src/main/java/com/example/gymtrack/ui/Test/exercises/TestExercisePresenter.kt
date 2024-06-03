package com.example.gymtrack.ui.Test.exercises

import com.example.gymtrack.data.model.Workouts

class TestExercisePresenter(private val view: TestExerciseContract.View,
                            private val selectedWorkout: Workouts
): TestExerciseContract.Presenter {

    override fun onViewCreated() {
        view.displayExercises(selectedWorkout.workout_exercises)
    }
}