package com.example.gymtrack.ui.Test

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gymtrack.R
import com.example.gymtrack.data.model.Users
import com.example.gymtrack.data.model.Workouts
import com.example.gymtrack.ui.Test.exercises.TestExercisesActivity
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class TestActivity : Fragment(), TestContract.View {
    private lateinit var presenter: TestContract.Presenter
    private val workouts = mutableListOf<Workouts>()
    private lateinit var workoutAdapter: TestAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_test, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Instantiate your TestAdapter
        workoutAdapter = TestAdapter(workouts) { selectedWorkout ->
            presenter.onWorkoutClicked(selectedWorkout)
        }

        // Set up RecyclerView
        val recyclerView = view.findViewById<RecyclerView>(R.id.test_rv)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = workoutAdapter

        presenter = TestPresenter(this)
        presenter.onViewCreated()
    }

    override fun displayWorkouts(fetchedWorkouts: List<Workouts>) {
        workouts.clear()
        workouts.addAll(fetchedWorkouts)
        workoutAdapter.notifyDataSetChanged()
    }

    override fun displayUserDetails(user: Users) {
        val userName = view?.findViewById<TextView>(R.id.user_name_tv)
        val userLastname = view?.findViewById<TextView>(R.id.user_lastname_tv)
        val usernameTV = view?.findViewById<TextView>(R.id.username_tv)

        userName?.text = user.user_name
        userLastname?.text = user.user_lastname
        usernameTV?.text = user.username
    }

    override fun displayError(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    override fun navigateToExercises(workout: Workouts) {
        val intent = Intent(activity, TestExercisesActivity::class.java)
        intent.putExtra("selected_workout", workout)
        startActivity(intent)
    }
}
