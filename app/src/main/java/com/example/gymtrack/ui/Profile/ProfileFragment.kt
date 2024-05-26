package com.example.gymtrack.ui.Profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.gymtrack.R

class ProfileFragment : Fragment() {

    private var currentView: View? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (currentView != null) {
            return currentView
        } else {
            currentView = inflater.inflate(R.layout.activity_profile, container, false)
            return currentView
        }
    }
}