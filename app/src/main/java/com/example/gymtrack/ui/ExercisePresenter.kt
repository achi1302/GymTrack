package com.example.gymtrack.ui

import android.content.Context
import com.example.gymtrack.data.model.DataManager
import com.example.gymtrack.data.model.ExerciseResponse
import com.example.gymtrack.util.SharedPreferencesConnector
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class ExercisePresenter(
    private val view: ExerciseActivity,
    private val context: Context
) : ExerciseContract.Presenter {
    private val connector = SharedPreferencesConnector.getInstance(context)
    private val dataManager = DataManager(context)

    override fun getExercise() {
        val observable = dataManager.getExercise()

        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Subscriber<ExerciseResponse>() {

                override fun onError(e: Throwable?) {
                    e?.printStackTrace()
                }

                override fun onNext(t: ExerciseResponse) {
                    view.showExercise(t.main)
                }

                override fun onCompleted() {
                    // no necesario
                }
            })
    }


}