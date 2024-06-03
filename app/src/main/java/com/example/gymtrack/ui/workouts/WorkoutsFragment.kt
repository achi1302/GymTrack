package com.example.gymtrack.ui.workouts

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gymtrack.R
import com.example.gymtrack.data.model.Workouts
import com.example.gymtrack.ui.workouts.start.WorkoutsStart
import java.io.Serializable


class WorkoutsFragment : Fragment(), WorkoutsContract.View {
    private lateinit var presenter: WorkoutsContract.Presenter
    private val workouts = mutableListOf<Workouts>()
    private lateinit var workoutAdapter: WorkoutsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_workouts, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Instantiate your TestAdapter
        workoutAdapter = WorkoutsAdapter(workouts) { selectedWorkout ->
            presenter.onWorkoutClicked(selectedWorkout)
        }

        // Set up RecyclerView
        val recyclerView = view.findViewById<RecyclerView>(R.id.workouts_rv)
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.adapter = workoutAdapter

        presenter = WorkoutsPresenter(this)
        presenter.onViewCreated()
    }

    override fun displayWorkouts(fetchedWorkouts: List<Workouts>) {
        workouts.clear()
        workouts.addAll(fetchedWorkouts)
        workoutAdapter.notifyDataSetChanged()
    }

//    override fun displayUserDetails(user: Users) {
//        val userName = view?.findViewById<TextView>(R.id.user_name_tv)
//        val userLastname = view?.findViewById<TextView>(R.id.user_lastname_tv)
//        val usernameTV = view?.findViewById<TextView>(R.id.username_tv)
//
//        userName?.text = user.user_name
//        userLastname?.text = user.user_lastname
//        usernameTV?.text = user.username
//    }

//    override fun displayError(message: String) {
//        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
//    }

    override fun navigateToExercises(workout: Workouts) {
        val intent = Intent(activity, WorkoutsStart::class.java)
            .apply {
                putExtra("selected_workout", workout as Serializable)
            }
        startActivity(intent)
    }
}
