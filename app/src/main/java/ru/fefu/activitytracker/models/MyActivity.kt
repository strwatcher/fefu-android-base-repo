package ru.fefu.activitytracker.models

import ru.fefu.activitytracker.views.main.ListItems

data class MyActivity(
    override val name: String,
    override val metric: String,
    override val startDate: Date,
    override val finishDate: Date,
    override val type: ListItems = ListItems.MyCard,
) : IListItem, IActivity
