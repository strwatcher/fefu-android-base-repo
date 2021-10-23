package ru.fefu.activitytracker.models

import ru.fefu.activitytracker.views.main.ListItems

data class DateSeparator(
    val date: DateTime,
    override val type: ListItems = ListItems.DateSeparator,
) : ListItemModel
