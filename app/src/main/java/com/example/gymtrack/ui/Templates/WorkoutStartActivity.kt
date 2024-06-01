package com.example.gymtrack.ui.Templates

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.gymtrack.R
import android.os.Handler
import android.widget.Button
import com.example.gymtrack.data.model.Workout
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore

class WorkoutStartActivity : AppCompatActivity(), WorkoutStartContract.View {

    private lateinit var presenter: WorkoutStartContract.Presenter
    private var workoutItemList : MutableList<Workout.ExerciseData> = mutableListOf()
    private lateinit var adapter: WorkoutStartAdapter

    private var startTime = 0L
    private var handler = Handler()
    private var runnable: Runnable? = null




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_workout_start)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        this.presenter = WorkoutStartPresenter(FirebaseFirestore.getInstance(), this)

        val timerTextView = findViewById<TextView>(R.id.timer)
        val finishButton = findViewById<Button>(R.id.finish_button)
        startTime = System.currentTimeMillis()

        runnable = object : Runnable {
            override fun run() {

                val millis = System.currentTimeMillis() - startTime
                var seconds = (millis / 1000).toInt()
                val minutes = seconds / 60
                seconds %= 60

                timerTextView.text = String.format("%02d:%02d", minutes, seconds)

                handler.postDelayed(this, 500)
            }
        }

        runnable.let {
            if (it != null) {
                handler.postDelayed(it, 0)
            }
        }

        finishButton.setOnClickListener {
            handler.removeCallbacks(runnable!!)
        }
        presenter.fetchWorkoutData()
    }
    override fun onDestroy() {
        super.onDestroy()
        if (runnable != null) handler.removeCallbacks(runnable!!)
    }



    override fun updateWorkoutItemList(workoutItems: List<Workout.ExerciseData>) {
        if (!workoutItems.isNullOrEmpty()) {
            workoutItemList.clear()
            workoutItemList.addAll(workoutItems)
            adapter.notifyDataSetChanged()
        }
    }
}