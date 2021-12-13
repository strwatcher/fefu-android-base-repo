package ru.fefu.activitytracker.extensions

import android.text.format.DateUtils
import java.time.Duration
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

fun LocalDateTime.toDateSeparator(): String {
    val dateTimeInMilliseconds = toInstant(ZoneOffset.UTC).toEpochMilli()

    val months = mapOf(
        1 to "Январь",
        2 to "Февраль",
        3 to "Март",
        4 to "Апрель",
        5 to "Май",
        6 to "Июнь",
        7 to "Июль",
        8 to "Август",
        9 to "Сентябрь",
        10 to "Октябрь",
        11 to "Ноябрь",
        12 to "Декабрь",
    )

    return when {
        DateUtils.isToday(dateTimeInMilliseconds) -> "Сегодня"
        DateUtils.isToday(dateTimeInMilliseconds + DateUtils.DAY_IN_MILLIS) -> "Вчера"
        else -> "${months[monthValue]} $year года"
    }
}

fun LocalDateTime.toFinishDateOrTime(): String {
    val curDateTime = LocalDateTime.now()

    val duration = Duration.between(this, curDateTime)

    return when {
        duration.toHours() < 24 -> "${duration.toHours()} ч. назад"
        else -> this.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))
    }
}


fun LocalDateTime.toTime(): String {
    return this.format(DateTimeFormatter.ofPattern("hh:mm"))
}

fun Duration.toFormattedDurationBetween(): String {
    return "${toHours()} ч. ${toMinutes() % 60} м."
}