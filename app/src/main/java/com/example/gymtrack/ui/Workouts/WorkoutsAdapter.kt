package com.example.gymtrack.ui.Workouts



import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gymtrack.R
import com.example.gymtrack.data.model.Workouts

class WorkoutsAdapter(private var workouts: List<Workouts>) : RecyclerView.Adapter<WorkoutsAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.workouts_workout_name)
        val descriptionTextView: TextView = itemView.findViewById(R.id.workouts_description)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.user_workouts_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = workouts.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val workout = workouts[position]
        holder.nameTextView.text = workout.workout_name
        holder.descriptionTextView.text = workout.workout_description
    }

    fun updateWorkouts(newList: List<Workouts>) {
        workouts = newList
        notifyDataSetChanged()
    }
}
