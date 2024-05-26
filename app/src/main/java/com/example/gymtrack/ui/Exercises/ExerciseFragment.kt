package com.example.gymtrack.ui.Exercises

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import android.view.View
import com.example.gymtrack.R
import com.example.gymtrack.data.model.Exercise

class ExerciseFragment : Fragment(), ExerciseContract.View  {

    private var presenter: ExercisePresenter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.activity_exercise, container, false)

        presenter = ExercisePresenter(this, requireContext())

        val buttonBiceps = view.findViewById<Button>(R.id.btnBiceps)
        buttonBiceps.setOnClickListener {
            presenter?.getExercise("biceps")
        }

        val buttonTriceps = view.findViewById<Button>(R.id.btnTriceps)
        buttonTriceps.setOnClickListener {
            presenter?.getExercise("triceps")
        }

        val buttonQuadriceps = view.findViewById<Button>(R.id.btnQuadriceps)
        buttonQuadriceps.setOnClickListener {
            presenter?.getExercise("quadriceps")
        }

        return view
    }

    override fun showExercise(exercise: List<Exercise>?) {
        var name = exercise?.get(0)?.name
        print(name)
        view?.findViewById<TextView>(R.id.exercises_title)?.text = name
    }

}