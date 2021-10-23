package ru.fefu.activitytracker.models

import ru.fefu.activitytracker.views.main.ListItems

data class UserActivity (
    val userName: String,
    val name: String,
    val metric: String,
    val time: TimeOffset,
    val date: DateTime,
    override val type: ListItems = ListItems.UserCard

) : ListItemModel
