package ru.fefu.activitytracker.models

class TimeOffset(
    private val hh: Int,
    private val mm: Int,
) {
    val formattedTime = "${formattedHour()}$mm ${minuteFormat()}"
    val formattedHoursAgo = "${formattedHour()}назад"
    val hours = hh
    private fun hourFormat(): String {
        return when {
            (hh != 11) and (hh % 10 == 1) -> {
                "час"
            }
            (hh != 12) and (hh != 13) and (hh != 14) and
                    ((hh % 10 == 2) or (hh % 10 == 3) or (hh % 10 == 4))
            -> {
                "часа"
            }
            else -> {
                "часов"
            }
        }
    }

    private fun minuteFormat(): String {
        return when {
            (mm != 11) and (mm % 10 == 1) -> {
                "минута"
            }
            (mm != 12) and (mm != 13) and (hh != 14) and
                    ((mm % 10 == 2) or (mm % 10 == 3) or (mm % 10 == 4))
            -> {
                "минуты"
            }
            else -> {
                "минут"
            }
        }
    }

    private fun formattedHour(): String {
        return if (hh == 0) {
            ""
        } else {
            "$hh ${hourFormat()} "
        }
    }


}