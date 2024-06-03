package com.example.gymtrack.ui.Test

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gymtrack.R
import com.example.gymtrack.data.model.Users
import com.example.gymtrack.data.model.Workouts
import com.example.gymtrack.ui.Test.exercises.TestExercisesActivity
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class TestActivity : AppCompatActivity(), TestContract.View {
    private lateinit var presenter: TestContract.Presenter
    private val workouts = mutableListOf<Workouts>()
    private lateinit var workoutAdapter: TestAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        // Instantiate your TestAdapter
        workoutAdapter = TestAdapter(workouts) { selectedWorkout ->
            presenter.onWorkoutClicked(selectedWorkout)
        }

        // Set up RecyclerView
        val recyclerView = findViewById<RecyclerView>(R.id.test_rv)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = workoutAdapter

        presenter = TestPresenter(this)
        presenter.onViewCreated()
    }

    override fun displayUserDetails(user: Users) {
        val userName = findViewById<TextView>(R.id.user_name_tv)
        val userLastname = findViewById<TextView>(R.id.user_lastname_tv)
        val usernameTV = findViewById<TextView>(R.id.username_tv)

        userName.text = user.user_name
        userLastname.text = user.user_lastname
        usernameTV.text = user.username
    }

    override fun displayWorkouts(fetchedWorkouts: List<Workouts>) {
        workouts.clear()
        workouts.addAll(fetchedWorkouts)
        workoutAdapter.notifyDataSetChanged()
    }

    override fun navigateToExercises(workout: Workouts) {
        val intent = Intent(this, TestExercisesActivity::class.java)
        intent.putExtra("selected_workout", workout)
        startActivity(intent)
    }

    override fun displayError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}
