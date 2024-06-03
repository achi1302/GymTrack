package com.example.gymtrack.ui.Workouts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gymtrack.R
import com.example.gymtrack.data.model.Workouts

class WorkoutsFragment : Fragment(), WorkoutsContract.View {

    private var currentView: View? = null
    private lateinit var presenter: WorkoutsPresenter
    private lateinit var adapter: WorkoutsAdapter
    private lateinit var workoutsRecyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (currentView != null) {
            return currentView
        } else {
            currentView = inflater.inflate(R.layout.activity_workouts, container, false)

            ViewCompat.setOnApplyWindowInsetsListener(currentView?.findViewById(R.id.main)!!) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }

            // Initialize presenter
            presenter = WorkoutsPresenter(this)

            // Set up RecyclerView
            workoutsRecyclerView = currentView!!.findViewById(R.id.workouts_recycler_view)
            adapter = WorkoutsAdapter(emptyList())
            workoutsRecyclerView.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            workoutsRecyclerView.adapter = adapter

            // Load workouts
            presenter.loadWorkouts()

            return currentView
        }
    }

    override fun showWorkouts(workouts: List<Workouts>) {
        adapter.updateWorkouts(workouts)
    }
}