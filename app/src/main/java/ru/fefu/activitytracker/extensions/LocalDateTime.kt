package ru.fefu.activitytracker.extensions

import android.os.Build
import android.text.format.DateUtils
import androidx.annotation.RequiresApi
import java.time.Duration
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
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

@RequiresApi(Build.VERSION_CODES.O)
fun LocalDateTime.toFinishDateOrTime(): String {
    val curDateTime = LocalDateTime.now()

    val duration = Duration.between(this, curDateTime)

    return when {
        duration.toHours() < 24 -> "${duration.toHours()} ч. назад"
        else -> this.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))
    }
}


@RequiresApi(Build.VERSION_CODES.O)
fun LocalDateTime.toTime(): String {
    return this.format(DateTimeFormatter.ofPattern("hh:mm"))
}

@RequiresApi(Build.VERSION_CODES.O)
fun Duration.toFormattedDurationBetween(): String {
    return "${toHours()} ч. ${toMinutes() % 60} м."
}