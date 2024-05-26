package com.example.gymtrack.data.model

import android.content.Context
import com.example.gymtrack.data.remote.api.exerciseAPI
import com.example.gymtrack.data.remote.client.ServiceGenerator
import com.example.gymtrack.util.SharedPreferencesConnector
import rx.Observable

class DataManager (val context: Context){
    private val connector = SharedPreferencesConnector.getInstance(context)

    fun getExercise(muscle: String)  : Observable<List<Exercise>> {
        return ServiceGenerator.createService(exerciseAPI::class.java,context).getExercise(muscle)

    }

}