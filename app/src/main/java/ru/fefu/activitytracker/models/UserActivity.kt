package ru.fefu.activitytracker.models

import ru.fefu.activitytracker.views.main.ListItems

data class UserActivity (
    val userName: String,
    override val name: String,
    override val metric: String,
    override val startDate: Date,
    override val finishDate: Date,
    override val type: ListItems = ListItems.UserCard
): IListItem, IActivity
