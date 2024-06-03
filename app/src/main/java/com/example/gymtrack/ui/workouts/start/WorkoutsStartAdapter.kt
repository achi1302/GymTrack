package com.example.gymtrack.ui.workouts.start

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gymtrack.R
import com.example.gymtrack.data.model.WorkoutExercises

class WorkoutsStartAdapter(private val workoutExercises: List<WorkoutExercises>) : RecyclerView.Adapter<WorkoutsStartAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.workout_start_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val exercise = workoutExercises[position]
        holder.setsTextView.text = exercise.sets.toString()
        holder.weightTextView.text = exercise.weight.toString()
        holder.repsTextView.text = exercise.reps.toString()
    }

    override fun getItemCount(): Int {
        return workoutExercises.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val setsTextView: TextView = itemView.findViewById(R.id.sets_textView)
        val weightTextView: TextView = itemView.findViewById(R.id.weight_textView)
        val repsTextView: TextView = itemView.findViewById(R.id.reps_textView)

    }
}