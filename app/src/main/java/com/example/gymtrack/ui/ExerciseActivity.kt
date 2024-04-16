package com.example.gymtrack.ui

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.gymtrack.R
import com.example.gymtrack.data.model.Exercise

class ExerciseActivity : AppCompatActivity() {

    private var presenter: ExercisePresenter? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise)
        presenter = ExercisePresenter(this, this)

        val buttonBiceps = findViewById<Button>(R.id.btnBiceps)
        buttonBiceps.setOnClickListener {
            presenter?.getExercise("biceps")
        }

        val buttonTriceps = findViewById<Button>(R.id.btnTriceps)
        buttonTriceps.setOnClickListener {
            presenter?.getExercise("triceps")
        }

        val buttonQuadriceps = findViewById<Button>(R.id.btnQuadriceps)
        buttonQuadriceps.setOnClickListener {
            presenter?.getExercise("quadriceps")
        }
    }

    private fun loadBicepData() {

    }

    fun showExercise(exercise: List<Exercise>?) {
        var name = exercise?.get(0)?.name.toString()
        print(name)
        findViewById<TextView>(R.id.exercises_title).text = "buenas tardes"
        //findViewById<TextView>(R.id.exercises_title).text = name

    }
}