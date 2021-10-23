package ru.fefu.activitytracker.models

class DateTime (
    private val yyyy: Int,
    private val MM: Int,
    private val dd: Int,
    private val hh: Int,
    private val mm: Int
): Comparable<DateTime> {

    private val months = mapOf(
        Pair(1, "Январь"),
        Pair(2, "Февраль"),
        Pair(3, "Март"),
        Pair(4, "Апрель"),
        Pair(5, "Май"),
        Pair(6, "Июнь"),
        Pair(7, "Июль"),
        Pair(8, "Август"),
        Pair(9, "Сентябрь"),
        Pair(10, "Октябрь"),
        Pair(11, "Ноябрь"),
        Pair(12, "Декабрь"),
    )

    val formattedDate
        get() = "${dd}.${MM}.${yyyy}"

    val formattedDateSeparator
        get() = "${months[MM]} $yyyy года"

    override fun compareTo(other: DateTime): Int {
        return -1  * ((yyyy - other.yyyy) * 12 +
                (MM - other.MM))
    }

}