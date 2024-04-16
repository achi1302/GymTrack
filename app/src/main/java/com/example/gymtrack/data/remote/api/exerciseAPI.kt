package com.example.gymtrack.data.remote.api

import com.example.gymtrack.data.model.ExerciseResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
import rx.Observable

interface exerciseAPI {
    @Headers("X-Api-Key: TF/9zcKT+ZGcaBVEX4SZ3A==Ksa6JG3Ie4C0g668")
    @GET("exercises?")
    fun getExercise(@Query("muscle") muscle : String) : Observable<ExerciseResponse>
}