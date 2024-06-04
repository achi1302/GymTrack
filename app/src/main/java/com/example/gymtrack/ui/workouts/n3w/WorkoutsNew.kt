package com.example.gymtrack.ui.workouts.n3w

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.gymtrack.R
import com.example.gymtrack.data.model.WorkoutExercises
import com.example.gymtrack.data.model.Workouts
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import java.util.UUID

class WorkoutsNew : AppCompatActivity() {
    private lateinit var workoutNameEditText: EditText
    private lateinit var categoryEditText: EditText
    private lateinit var descriptionEditText: EditText
    private lateinit var exerciseNameEditText: EditText
    private lateinit var setsEditText: EditText
    private lateinit var repsEditText: EditText
    private lateinit var weightEditText: EditText
    private lateinit var newExerciseButton: Button
    private lateinit var submitButton: Button

    private val workoutExercisesList = mutableListOf<WorkoutExercises>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_workout_new)

        // Get references to views in the layout
        workoutNameEditText = findViewById(R.id.editTextWorkoutName)
        exerciseNameEditText = findViewById(R.id.editTextExerciseName)
        categoryEditText = findViewById(R.id.editTextCategory)
        descriptionEditText = findViewById(R.id.editTextDescription)
        setsEditText = findViewById(R.id.editTextSets)
        repsEditText = findViewById(R.id.editTextReps)
        weightEditText = findViewById(R.id.editTextWeight)
        newExerciseButton = findViewById(R.id.buttonNewExercise)
        submitButton = findViewById(R.id.buttonSubmitWorkout)

        // Initialize Firestore
        val db = Firebase.firestore

        newExerciseButton.setOnClickListener {
            // get form inputs for new exercise
            val exerciseName = exerciseNameEditText.text.toString()
            val sets = setsEditText.text.toString().toIntOrNull() ?: 0
            val reps = repsEditText.text.toString().toIntOrNull() ?: 0
            val weight = weightEditText.text.toString().toIntOrNull() ?: 0

            // generate a unique id for the new exercise
            val exerciseId = UUID.randomUUID().toString()

            val newWorkoutExercise = WorkoutExercises(exerciseId, exerciseName, sets, reps, weight)

            // Add new workoutExercise to the list
            workoutExercisesList.add(newWorkoutExercise)



            // Clear the exercise fields for a new input
            exerciseNameEditText.setText("")
            setsEditText.setText("")
            repsEditText.setText("")
            weightEditText.setText("")

            // Display toast message to alert the user that the exercise was added
            Toast.makeText(this, "Exercise added successfully!", Toast.LENGTH_SHORT).show()
        }

        submitButton.setOnClickListener {
            // Get form inputs for the workout
            val workoutName = workoutNameEditText.text.toString()
            val category = categoryEditText.text.toString()
            val description = descriptionEditText.text.toString()

            // Generate unique id for the workout
            val workoutId = UUID.randomUUID().toString()

            // Create Workouts Object with all workoutExercises
            val workout = Workouts(
                workoutId,
                workoutName,
                category, // you might want to handle workout category separately
                description, // you might want to handle workout description separately
                workoutExercisesList // This will include all the exercises added by user
            )

            val workoutsCollection = db.collection("Workouts")
            // Create a new document in 'Workouts' collection
            val documentReference = workoutsCollection.document(workoutId)

            // Write workout to Firestore
            db.collection("Workouts").document(workoutId)
                .set(workout)
                .addOnSuccessListener {
                    Log.d("CreateWorkoutActivity", "Workout successfully written to Firestore!")

                    // Clear all fields and list after successful submission
                    workoutNameEditText.setText("")
                    workoutExercisesList.clear()
                }
                .addOnFailureListener { e ->
                    Log.d("CreateWorkoutActivity", "Error writing Workout to Firestore", e)
                }

            Toast.makeText(this, "Workout added successfully!", Toast.LENGTH_SHORT).show()
        }
    }
}