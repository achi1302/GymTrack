package com.example.gymtrack.ui.Workouts.start

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gymtrack.R
import com.example.gymtrack.data.model.WorkoutExercises

class WorkoutStartActivity : AppCompatActivity(), WorkoutsStartContract.View {
    private lateinit var presenter: WorkoutsStartPresenter
    private lateinit var adapter: WorkoutStartAdapter
    private lateinit var exerciseRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_workout_start)

        // Initialize presenter
        presenter = WorkoutsStartPresenter(this)

        // Setup RecyclerView
        exerciseRecyclerView = findViewById(R.id.workout_start_rv)

        // Initialize the adapter and set it on the RecyclerView upfront
        adapter = WorkoutStartAdapter(emptyList())
        exerciseRecyclerView.adapter = adapter
        exerciseRecyclerView.layoutManager = LinearLayoutManager(this)

        // Load exercises
        presenter.loadExercises()
    }

    // Updated showExercises method to notify the adapter of changes instead of creating a new instance.
    override fun showExercises(exercises: List<WorkoutExercises>) {
        adapter.updateExercises(exercises)
    }
}


