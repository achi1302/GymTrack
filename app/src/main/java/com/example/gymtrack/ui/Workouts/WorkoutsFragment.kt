package com.example.gymtrack.ui.Workouts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.gymtrack.R

class WorkoutsFragment : Fragment() {

    private var currentView: View? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (currentView != null) {
            return currentView
        } else {
            currentView = inflater.inflate(R.layout.activity_workouts, container, false)

            // Move your logic here
            ViewCompat.setOnApplyWindowInsetsListener(currentView?.findViewById(R.id.main)!!) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }

            return currentView
        }
    }
}