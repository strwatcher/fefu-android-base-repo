package ru.fefu.activitytracker.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MyActivity(
    val id: Int,
    override val name: String,
    override val metric: String,
    override val finishDate: String,
    override val duration: String,
    override val startTime: String,
    override val finishTime: String,
    override val type: ListItems = ListItems.MyCard
) : IActivity, Parcelable
