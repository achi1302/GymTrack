package com.example.gymtrack.ui.profile

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gymtrack.R
import com.example.gymtrack.data.model.Users
import com.example.gymtrack.data.model.Workouts
import com.example.gymtrack.ui.profile.ProfileAdapter
import com.example.gymtrack.ui.profile.ProfileContract
import com.example.gymtrack.ui.profile.ProfilePresenter
import com.example.gymtrack.ui.workouts.n3w.WorkoutsNew
import com.example.gymtrack.ui.workouts.start.WorkoutsStart
import java.io.Serializable


class ProfileFragment : Fragment(), ProfileContract.View {
    private lateinit var presenter: ProfileContract.Presenter
    private val workouts = mutableListOf<Workouts>()
    private lateinit var profileAdapter: ProfileAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Instantiate your TestAdapter
        profileAdapter = ProfileAdapter(workouts) { selectedWorkout ->
            presenter.onWorkoutClicked(selectedWorkout)
        }

        val recyclerView = view.findViewById<RecyclerView>(R.id.profile_rv)
        recyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = profileAdapter

        presenter = ProfilePresenter(this)
        presenter.onViewCreated()


    }

    override fun displayWorkouts(fetchedWorkouts: List<Workouts>) {
        workouts.clear()
        workouts.addAll(fetchedWorkouts)
        profileAdapter.notifyDataSetChanged()
    }

    override fun displayUserDetails(user: Users) {
        val userName = view?.findViewById<TextView>(R.id.name_tv)
        val userLastname = view?.findViewById<TextView>(R.id.lasstname_tv)
        val username = view?.findViewById<TextView>(R.id.username_tv)

        userName?.text = user.user_name
        userLastname?.text = user.user_lastname
        username?.text = user.username
    }

    override fun navigateToExercises(workout: Workouts) {
        Log.d("WorkoutsFragment", "Navigating to WorkoutsStart with workout: $workout")
        val intent = Intent(activity, WorkoutsStart::class.java)
            .apply {
                putExtra("selected_workout", workout as Serializable)
            }
        startActivity(intent)
    }
}




