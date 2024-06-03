package com.example.gymtrack.ui.Workouts.start

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gymtrack.R
import com.example.gymtrack.data.model.WorkoutExercises

class WorkoutStartAdapter (private var exercises: List<WorkoutExercises>) : RecyclerView.Adapter<WorkoutStartAdapter.ExerciseViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.exercise_workout_item, parent, false)
        return ExerciseViewHolder(view)
    }

    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int){
        val exercise = exercises[position]
        holder.repsTextView.text = exercise.reps.toString()
        holder.setsTextView.text = exercise.sets.toString()
        holder.weightTextView.text = exercise.weight.toString()
    }

    override fun getItemCount(): Int = exercises.size

    // This will hold your Android views
    class ExerciseViewHolder(itemView: View) :  RecyclerView.ViewHolder(itemView){
        val repsTextView: TextView = itemView.findViewById(R.id.reps)
        val setsTextView: TextView = itemView.findViewById(R.id.sets)
        val weightTextView: TextView = itemView.findViewById(R.id.weight)
    }

    fun updateExercises(newExercises: List<WorkoutExercises>) {
        exercises = newExercises
        notifyDataSetChanged()
    }


}



