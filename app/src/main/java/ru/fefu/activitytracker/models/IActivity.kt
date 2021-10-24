package ru.fefu.activitytracker.models

interface IActivity: IListItem {
    val name: String
    val metric: String
    val startDate: Date
    val finishDate: Date
}