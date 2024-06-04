package com.example.gymtrack.ui.workouts.n3w

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.gymtrack.R
import com.example.gymtrack.data.model.WorkoutExercises
import com.example.gymtrack.data.model.Workouts
import com.google.firebase.Firebase
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.firestore
import java.util.UUID

class WorkoutsNew : AppCompatActivity(), WorkoutsNewContract.View {

    private lateinit var presenter: WorkoutsNewContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_workout_new)
        //... other init codes

        presenter = WorkoutsNewPresenter(this)

        val newExerciseButton = findViewById<Button>(R.id.buttonNewExercise)
        newExerciseButton.setOnClickListener {
            // get form inputs for new exercise
            val exerciseName = findViewById<EditText>(R.id.new_ExerciseName).text.toString()
            val sets = findViewById<EditText>(R.id.new_Sets).text.toString().toIntOrNull() ?: 0
            val weight = findViewById<EditText>(R.id.new_Weight).text.toString().toIntOrNull() ?: 0
            val reps = findViewById<EditText>(R.id.new_Reps).text.toString().toIntOrNull() ?: 0


            presenter.addExercise(exerciseName, reps, sets, weight)
        }

        val submitButton = findViewById<Button>(R.id.buttonSubmitWorkout)
        submitButton.setOnClickListener {
            val workoutName = findViewById<EditText>(R.id.new_WorkoutName).text.toString()
            val category = findViewById<Spinner>(R.id.new_Category).selectedItem.toString()
            val description = findViewById<EditText>(R.id.new_Description).text.toString()

            presenter.submitWorkout(workoutName, category, description)

            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
    }

    override fun onExerciseAdded() {
        findViewById<EditText>(R.id.new_ExerciseName).setText("")
        findViewById<EditText>(R.id.new_Sets).setText("")
        findViewById<EditText>(R.id.new_Weight).setText("")
        findViewById<EditText>(R.id.new_Reps).setText("")
        Toast.makeText(this, "Exercise added successfully!", Toast.LENGTH_SHORT).show()
    }

    override fun onWorkoutAdded() {
        Toast.makeText(this, "Workout added successfully!", Toast.LENGTH_SHORT).show()
    }

    override fun onError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}