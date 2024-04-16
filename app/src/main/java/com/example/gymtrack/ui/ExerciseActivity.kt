package com.example.gymtrack.ui

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.gymtrack.R
import com.example.gymtrack.data.model.Exercise
import com.example.gymtrack.data.model.ExerciseResponse

class ExerciseActivity : AppCompatActivity() {

    private var presenter: ExercisePresenter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise)
        presenter = ExercisePresenter(this, this)
        loadInitialData()
    }

    private fun loadInitialData() {
        presenter?.getExercise()
    }

    fun showExercise(exercise: List<Exercise>?) {
        var name = exercise?.get(0)?.name.toString()
        print(name)
        findViewById<TextView>(R.id.exercises_title).text = "buenas tardes"
        //findViewById<TextView>(R.id.exercises_title).text = name

    }
}