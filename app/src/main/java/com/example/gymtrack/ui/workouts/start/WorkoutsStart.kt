package com.example.gymtrack.ui.workouts.start

import android.os.Bundle
import android.os.Handler
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NavUtils
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gymtrack.R
import com.example.gymtrack.data.model.WorkoutExercises
import com.example.gymtrack.data.model.Workouts
import java.util.Locale

class WorkoutsStart: AppCompatActivity(), WorkoutsStartContract.View {
    private lateinit var presenter: WorkoutsStartContract.Presenter
    private lateinit var exerciseAdapter: WorkoutsStartAdapter

    private val handler = Handler()
    private var secondsCount = 0
    private var isTimerRunning = false
    private lateinit var runnable: Runnable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_workout_start)

        runnable = object : Runnable {
            override fun run() {
                val hours = secondsCount / 3600
                val minutes = secondsCount % 3600 / 60
                val secs = secondsCount % 60
                findViewById<TextView>(R.id.timer).text = String.format(Locale.getDefault(), "%02d:%02d:%02d", hours, minutes, secs)
                if(isTimerRunning) {
                    secondsCount++
                }
                handler.postDelayed(this, 1000)
            }
        }

        findViewById<Button>(R.id.finish_button).setOnClickListener {
            isTimerRunning = false
        }

        val selectedWorkout: Workouts = intent.getSerializableExtra("selected_workout") as Workouts
        presenter = WorkoutsStartPresenter(this, selectedWorkout)

        val workoutNameTextView: TextView = findViewById(R.id.workout_name)
        workoutNameTextView.text = selectedWorkout.workout_name

        exerciseAdapter = WorkoutsStartAdapter(selectedWorkout.workout_exercises)

        val recyclerView = findViewById<RecyclerView>(R.id.workout_start_rv)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter =  exerciseAdapter

        presenter.onViewCreated()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        handler.post(runnable)
        isTimerRunning = true

    }

    override fun displayExercises(exercises: List<WorkoutExercises>) {
        exerciseAdapter.notifyDataSetChanged()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                // Respond to the action bar's Up/Home button
                NavUtils.navigateUpFromSameTask(this)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}