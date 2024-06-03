package com.example.gymtrack.ui.Test

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gymtrack.R
import com.example.gymtrack.data.model.WorkoutExercises
import com.example.gymtrack.data.model.Workouts

class TestExercisesActivity : AppCompatActivity() {

    private lateinit var exercises: List<WorkoutExercises>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_exercises)

        //Get selected workout from intent
        val selectedWorkout: Workouts = intent.getSerializableExtra("selected_workout") as Workouts

        //Get list of exercises from selected Workout
        exercises = selectedWorkout.workout_exercises

        //Initialize adapter for Recyclerview
        val exerciseAdapter = TestExerciseAdapter(exercises)

        //Set up the RecyclerView
        val recyclerView = findViewById<RecyclerView>(R.id.test_exercises_rv)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter =  exerciseAdapter
    }
}