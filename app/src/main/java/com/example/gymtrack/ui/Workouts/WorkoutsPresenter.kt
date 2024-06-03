package com.example.gymtrack.ui.Workouts

import android.util.Log
import com.example.gymtrack.data.model.Users
import com.example.gymtrack.data.model.Workouts
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class WorkoutsPresenter(private val view: WorkoutsFragment): WorkoutsContract.Presenter {
    private val db = Firebase.firestore

    override fun loadWorkouts() {
        val docRef = db.collection("Users").document("FWAzJkVoLpRsApIk3PPY")
        docRef.get().addOnSuccessListener { document ->
            val user = document.toObject(Users::class.java)

            // Initialize the list to store workouts
            val workoutsList = mutableListOf<Workouts>()

            val allWorkouts = user?.user_workouts

            if (allWorkouts != null) {
                var counter = 0
                for (workoutDocRef in allWorkouts) {
                    workoutDocRef.get().addOnSuccessListener { workoutSnapshot ->
                        val workout = workoutSnapshot.toObject(Workouts::class.java)
                        workoutsList.add(workout!!)
                        counter++

                        if (counter == allWorkouts.size) {
                            Log.d("WorkoutsPresenter", "Workouts fetched: $workoutsList")
                            // Display the fetched workouts once all of them are ready
                            view.showWorkouts(workoutsList)
                        }
                    }
                }
            }
        }
    }
}