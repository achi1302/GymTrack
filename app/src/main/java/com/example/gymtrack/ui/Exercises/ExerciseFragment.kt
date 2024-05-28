package com.example.gymtrack.ui.Exercises

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gymtrack.R
import com.example.gymtrack.data.model.Exercise

class ExerciseFragment : Fragment(), ExerciseContract.View {

    private var currentView: View? = null
    private var presenter: ExercisePresenter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (currentView != null) {
            return currentView
        }else{
            currentView = inflater.inflate(R.layout.activity_exercise, container, false)
            return currentView
        }

    }

    override fun onViewCreated(view:View, savedInstanceState: Bundle?){
        presenter = context?.let { ExercisePresenter(this, it) }

        val buttonAbs = view.findViewById<Button>(R.id.btnAbs)
        buttonAbs.setOnClickListener {
            presenter?.getExercise("abdominals")
        }

        val buttonBiceps = view.findViewById<Button>(R.id.btnBiceps)
        buttonBiceps.setOnClickListener {
            presenter?.getExercise("biceps")
        }

        val buttonCalves = view.findViewById<Button>(R.id.btnCalves)
        buttonCalves.setOnClickListener {
            presenter?.getExercise("calves")
        }

        val buttonChest = view.findViewById<Button>(R.id.btnChest)
        buttonChest.setOnClickListener {
            presenter?.getExercise("chest")
        }

        val buttonForearms = view.findViewById<Button>(R.id.btnForearms)
        buttonForearms.setOnClickListener {
            presenter?.getExercise("forearms")
        }

        val buttonGlutes = view.findViewById<Button>(R.id.btnGlutes)
        buttonGlutes.setOnClickListener {
            presenter?.getExercise("glutes")
        }

        val buttonHammies = view.findViewById<Button>(R.id.btnHamstrings)
        buttonHammies.setOnClickListener {
            presenter?.getExercise("hamstrings")
        }

        val buttonLats = view.findViewById<Button>(R.id.btnLats)
        buttonLats.setOnClickListener {
            presenter?.getExercise("lats")
        }

        val buttonLowerBack = view.findViewById<Button>(R.id.btnLowerBack)
        buttonLowerBack.setOnClickListener {
            presenter?.getExercise("lower_back")
        }

        val buttonMiddleBack = view.findViewById<Button>(R.id.btnMiddleBack)
        buttonMiddleBack.setOnClickListener {
            presenter?.getExercise("middle_back")
        }

        val buttonQuads = view.findViewById<Button>(R.id.btnQuads)
        buttonQuads.setOnClickListener {
            presenter?.getExercise("quadriceps")
        }

        val buttonTraps = view.findViewById<Button>(R.id.btnTraps)
        buttonTraps.setOnClickListener {
            presenter?.getExercise("traps")
        }

        val buttonTriceps = view.findViewById<Button>(R.id.btnTriceps)
        buttonTriceps.setOnClickListener {
            presenter?.getExercise("triceps")
        }

    }


    override fun showExercise(exercise: List<Exercise>?) {

        val exerciseList = view?.findViewById<RecyclerView>(R.id.exercise_Container)


        val timeAdapter = exercise?.let { ExerciseAdapter(it) } // (2)
        if (exerciseList != null) {
            exerciseList.layoutManager = LinearLayoutManager(context)
            exerciseList.adapter = timeAdapter
        } // (3)
        timeAdapter?.setOnClickListener(object : ExerciseAdapter. OnContractClickListener{
            override fun onClick(position: Int, exercise: Exercise) {
                presenter?.triggerDialogInfo(exercise)
            }
        })


    }
    override fun showDialogInfo(exercise: Exercise) {
        val dialog = AlertDialog.Builder(context).create()
        val dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_exercise_instructions, null)

        val diaMuscle = dialogView.findViewById<TextView>(R.id.dia_muscle)
        val diaInstructions = dialogView.findViewById<TextView>(R.id.dia_instructions)

        diaMuscle.text = exercise.name
        diaInstructions.text = exercise.instructions

        dialog.setView(dialogView)

        val btnAdd = dialogView.findViewById<Button>(R.id.btn_add)
        val btnCancel = dialogView.findViewById<Button>(R.id.btn_cancel)

        btnAdd.setOnClickListener {
            // Code to be executed
            dialog.dismiss()
        }

        btnCancel.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()


    }

}












