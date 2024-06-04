package com.example.gymtrack.ui.workouts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gymtrack.R
import com.example.gymtrack.data.model.Workouts

class WorkoutsAdapter(
    private val workouts: List<Workouts>,
    private val onWorkoutItemClick: (Workouts) -> Unit
) : RecyclerView.Adapter<WorkoutsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.workouts_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val workout = workouts[position]
        holder.workoutNameTextView.text = workout.workout_name
        holder.workoutDescriptionTextView.text = workout.workout_description
        holder.workoutCategoryTextView.text = workout.workout_category
        holder.itemView.setOnClickListener { onWorkoutItemClick(workout) }
    }

    override fun getItemCount(): Int {
        return workouts.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val workoutNameTextView: TextView = itemView.findViewById(R.id.workouts_item_workoutname)
        val workoutDescriptionTextView: TextView = itemView.findViewById(R.id.workouts_item_workoutdescription)
        val workoutCategoryTextView: TextView = itemView.findViewById(R.id.workouts_item_workoutcategory)
    }
}