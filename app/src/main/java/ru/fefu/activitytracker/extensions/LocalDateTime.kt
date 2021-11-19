package ru.fefu.activitytracker.extensions

import android.os.Build
import android.text.format.DateUtils
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneOffset

@RequiresApi(Build.VERSION_CODES.O)
fun LocalDateTime.toFormattedDate(): String {
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