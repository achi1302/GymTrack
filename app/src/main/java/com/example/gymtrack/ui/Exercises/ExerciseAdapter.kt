package com.example.gymtrack.ui.Exercises

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gymtrack.R
import com.example.gymtrack.data.model.Exercise

class ExerciseAdapter(private val exerciseList: List<Exercise>) : RecyclerView.Adapter<ExerciseAdapter.ExerciseViewHolder>() {


    private var onContractClickListenerInstance: OnContractClickListener? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recyclerview_item, parent, false)
        return ExerciseViewHolder(view)
    }

    override fun getItemCount() = exerciseList.size

    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        val exercise = exerciseList[position]
        holder.bind(exercise)
        holder.itemView.setOnClickListener {
            onContractClickListenerInstance?.onClick(position, exercise)
        }

    }

    fun setOnClickListener(onContractClickListener: OnContractClickListener) {
        this.onContractClickListenerInstance = onContractClickListener
    }

    inner class ExerciseViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameTextView: TextView = view.findViewById(R.id.item_name)
//      val instructionTextView: TextView = view.findViewById(R.id.temp_instruction)
        fun bind(exercise: Exercise?) {
            if (exercise != null) {
                nameTextView.text = exercise.name?.toString()

            }

        }



    }
    interface OnContractClickListener {
        fun onClick(position: Int, exercise: Exercise)
    }
}

