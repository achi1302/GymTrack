package com.example.gymtrack.ui.Exercises

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gymtrack.R
import com.example.gymtrack.data.model.Exercise

class ExerciseAdapter(private val exerciseList: List<Exercise>) : RecyclerView.Adapter<ExerciseAdapter.ExerciseViewHolder>() {

    inner class ExerciseViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameTextView: TextView = view.findViewById(R.id.item_name)
//        val instructionTextView: TextView = view.findViewById(R.id.temp_instruction)
        // Add ImageView if you are going to use it in this example doesn't include imageView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item, parent, false)
        return ExerciseViewHolder(view)
    }

    override fun getItemCount() = exerciseList.size

    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        val exercise = exerciseList[position]
        holder.nameTextView.text = exercise.name // assuming 'name' is a property in Exercise class
//        holder.instructionTextView.text = exercise.instructions // assuming 'instructions' is a property in Exercise class
    }
}