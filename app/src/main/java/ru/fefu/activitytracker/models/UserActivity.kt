package ru.fefu.activitytracker.models

import ru.fefu.activitytracker.views.main.ListItems

data class UserActivity (
    val userName: String,
    val userComment: String,
    override val name: String,
    override val metric: String,
    override val finishDate: String,
    override val duration: String,
    override val startTime: String,
    override val finishTime: String,
    override val type: ListItems = ListItems.UserCard,
): IListItem, IActivity
