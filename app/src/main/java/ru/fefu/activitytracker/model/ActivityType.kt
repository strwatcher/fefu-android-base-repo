package ru.fefu.activitytracker.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
enum class ActivityType(val title: String) : Parcelable {
    Run("Бег"),
    Hike("Ходьба"),
    Bicycle("Велосипед")
}