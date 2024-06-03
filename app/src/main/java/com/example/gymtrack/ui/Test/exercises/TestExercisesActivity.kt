package com.example.gymtrack.ui.Test.exercises

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gymtrack.R
import com.example.gymtrack.data.model.WorkoutExercises
import com.example.gymtrack.data.model.Workouts

class TestExercisesActivity: AppCompatActivity(), TestExerciseContract.View {
    private lateinit var presenter: TestExerciseContract.Presenter
    private lateinit var exerciseAdapter: TestExerciseAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_exercises)

        val selectedWorkout: Workouts = intent.getSerializableExtra("selected_workout") as Workouts
        presenter = TestExercisePresenter(this, selectedWorkout)

        exerciseAdapter = TestExerciseAdapter(selectedWorkout.workout_exercises)

        val recyclerView = findViewById<RecyclerView>(R.id.test_exercises_rv)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter =  exerciseAdapter

        presenter.onViewCreated()

    }

    override fun displayExercises(exercises: List<WorkoutExercises>) {
        exerciseAdapter.notifyDataSetChanged()
    }
}