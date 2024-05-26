package com.example.gymtrack.ui.Exercises

import android.content.Context
import com.example.gymtrack.data.model.DataManager
import com.example.gymtrack.data.model.Exercise
import com.example.gymtrack.util.SharedPreferencesConnector
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class ExercisePresenter(
    private val view: ExerciseContract.View,
    private val context: Context
) : ExerciseContract.Presenter {

    private val connector = SharedPreferencesConnector.getInstance(context)
    private val dataManager = DataManager(context)

    override fun getExercise(muscle:String) {
        val observable = dataManager.getExercise(muscle)

        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Subscriber<List<Exercise>>() {

                override fun onError(e: Throwable?) {
                    e?.printStackTrace()
                }

                override fun onNext(t: List<Exercise>) {
                    view.showExercise(t)
                }

                override fun onCompleted() {
                    // not necessary
                }
            })
    }
}