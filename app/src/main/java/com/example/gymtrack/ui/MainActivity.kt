package com.example.gymtrack.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.gymtrack.R
import com.example.gymtrack.ui.Exercises.ExerciseFragment
import com.example.gymtrack.ui.Profile.ProfileFragment
import com.example.gymtrack.ui.Test.TestActivity
import com.example.gymtrack.ui.Workouts.WorkoutsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    var bottomNav: BottomNavigationView? = null
//    private var presenter: MainPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottomNav = findViewById<BottomNavigationView>(R.id.navigation)

//        presenter = MainPresenter(this, this)
        loadInitialdata()
    }

    private fun loadInitialdata() {
//        presenter?.getWeather()

        bottomNav?.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.item1 -> {
                    loadFragment(ProfileFragment())
                    true
                }

                R.id.item2 -> {
                    loadFragment(TestActivity())
                    true
                }

                R.id.item3 -> {
                        loadFragment(ExerciseFragment())
                    true
                }

                else -> {
                    //  loadFragment(HomeFragment())
                    true
                }
            }
        }

        bottomNav!!.selectedItemId = R.id.item2

    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.main_container, fragment).commit()
    }
}