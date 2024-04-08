package com.example.bsagroupproject.data

import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.DrawableRes
import com.example.bsagroupproject.R


data class Person(
    val id:Int=0,
    val name: String? =" ",
    @DrawableRes val icon:Int= R.drawable.icon_facebook
):Parcelable {

    val personList = listOf(
        Person(
            1,
            "Rajat",
            R.drawable.icon_facebook
        ),
        Person(
            2,
            "Rohit",
            R.drawable.icon_instagram
        ),
        Person(
            3,
            "Chirag",
            R.drawable.icon_facebook
        ),
        Person(
            4,
            "Sourabh",
            R.drawable.icon_instagram
        ),
        Person(
            5,
            "Roshini",
            R.drawable.icon_facebook
        ),
        Person(
            6,
            "Kaushik",
            R.drawable.ic_launcher_foreground
        ),
        Person(
            7,
            "Ayesha",
            R.drawable.chat_bubble_outline_badged
        ),
    )

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeInt(icon)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Person> {
        override fun createFromParcel(parcel: Parcel): Person {
            return Person(parcel)
        }

        override fun newArray(size: Int): Array<Person?> {
            return arrayOfNulls(size)
        }
    }
}