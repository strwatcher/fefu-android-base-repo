package ru.fefu.activitytracker.model

interface IActivity: IListItem {
    val name: String
    val metric: String
    val finishDate: String
    val duration: String
    val startTime: String
    val finishTime: String
    override val type: ListItems
}