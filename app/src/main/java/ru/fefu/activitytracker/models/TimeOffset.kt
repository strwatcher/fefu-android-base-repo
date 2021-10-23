package ru.fefu.activitytracker.models

class TimeOffset(
    private val hh: Int,
    private val mm: Int,
) {
    val formattedTime = "${hh}ч ${mm}м"
}