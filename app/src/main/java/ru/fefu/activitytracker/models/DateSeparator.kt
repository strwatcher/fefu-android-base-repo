package ru.fefu.activitytracker.models

import ru.fefu.activitytracker.views.main.ListItems

data class DateSeparator(
    val formattedDate: String,
    override val type: ListItems = ListItems.DateSeparator,
) : ListItemModel
