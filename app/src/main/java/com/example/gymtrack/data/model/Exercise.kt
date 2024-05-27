package com.example.gymtrack.data.model

import android.os.Parcel
import android.os.Parcelable

class Exercise( val name: String?,
//               val type: String?,
//               val muscle: String?,
//               val equipment: String?,
//               val difficulty: String?,
               val instructions: String?)
        : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),


    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
//        parcel.writeString(type)
//        parcel.writeString(muscle)
//        parcel.writeString(equipment)
//        parcel.writeString(difficulty)
        parcel.writeString(instructions)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Exercise> {
        override fun createFromParcel(parcel: Parcel): Exercise {
            return Exercise(parcel)
        }

        override fun newArray(size: Int): Array<Exercise?> {
            return arrayOfNulls(size)
        }
    }
}