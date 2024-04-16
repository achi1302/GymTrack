package com.example.gymtrack.data.model

import android.os.Parcel
import android.os.Parcelable

class ExerciseResponse (
    val main : List<Exercise>?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.createTypedArrayList(Exercise.CREATOR)
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeTypedList(main)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ExerciseResponse> {
        override fun createFromParcel(parcel: Parcel): ExerciseResponse {
            return ExerciseResponse(parcel)
        }

        override fun newArray(size: Int): Array<ExerciseResponse?> {
            return arrayOfNulls(size)
        }
    }
}