package ru.fefu.activitytracker.models

import ru.fefu.activitytracker.views.main.ListItems

data class MyActivity(
    val name: String,
    val metric: String,
    val startDate: Date,
    val finishDate: Date,
    override val type: ListItems = ListItems.MyCard,
) : ListItemModel
