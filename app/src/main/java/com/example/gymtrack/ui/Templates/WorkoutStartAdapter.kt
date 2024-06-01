package com.example.gymtrack.ui.Templates

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gymtrack.R
import com.example.gymtrack.data.model.Workout

class WorkoutStartAdapter(private var workoutList: List<Workout.ExerciseData>) :
    RecyclerView.Adapter<WorkoutStartAdapter.WorkoutItemViewHolder>() {

    inner class WorkoutItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        val exerciseNameTextView: TextView = itemView.findViewById(R.id.exercise_name)
        val setsTextView: TextView = itemView.findViewById(R.id.sets)
        val weightTextView: TextView = itemView.findViewById(R.id.weight)
        val repsTextView: TextView = itemView.findViewById(R.id.reps)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkoutItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.exercise_workout_item, parent, false)
        return WorkoutItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: WorkoutItemViewHolder, position: Int) {
        val currentItem = workoutList[position]
        holder.setsTextView.text = currentItem.sets.toString()
        holder.weightTextView.text = currentItem.weight.toString()
        holder.repsTextView.text = currentItem.reps.toString()
//        holder.exerciseNameTextView.text = currentItem.exercise_id
    }

    override fun getItemCount(): Int {
        return workoutList.size
    }
}