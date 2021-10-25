package ru.fefu.activitytracker.models

import java.util.*

class Date (
    private var yyyy: Int,
    private var MM: Int,
    private var dd: Int,
    private var hh: Int,
    private var mm: Int
): Comparable<Date> {

    constructor(): this(0, 0, 0, 0, 0)

    constructor(calendar: GregorianCalendar): this(
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH) + 1,
        calendar.get(Calendar.DATE),
        calendar.get(Calendar.HOUR_OF_DAY),
        calendar.get(Calendar.MINUTE)
    )

    companion object {
        @JvmStatic
        fun now(): Date {
            val calendar = GregorianCalendar(Locale.getDefault())
            return Date(calendar)
        }
    }

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
        get() =
            "${dd.toString().padStart(2, '0')}.${MM.toString().padStart(2, '0')}.${yyyy}"


    val formattedDateSeparator
        get() = "${months[MM]} $yyyy года"

    val formattedTime
        get() = "${hh.toString().padStart(2, '0')}:${mm.toString().padStart(2, '0')}"

    override fun compareTo(other: Date): Int {
        return -1 * ((yyyy - other.yyyy) * 365 + (MM - other.MM) * 30 + (dd - other.dd))
    }

    override fun hashCode(): Int {
        var result = yyyy
        result = 31 * result + MM
        result = 31 * result + dd
        result = 31 * result + hh
        result = 31 * result + mm
        return result
    }

    override fun equals(other: Any?): Boolean {
       return hashCode() == other.hashCode()
    }

    fun equalsUpToMonth(other: Date): Boolean {
        return (yyyy == other.yyyy) and (MM == other.MM)
    }

    fun equalsUpToDay(other: Date): Boolean {
        return equalsUpToMonth(other) and (dd == other.dd)
    }

    fun equalsUpToHour(other: Date): Boolean {
        return equalsUpToDay(other) and (hh == other.hh)
    }

    fun minus(
        yyyyOffset: Int = 0,
        MMOffset: Int = 0,
        ddOffset: Int = 0,
        hhOffset: Int = 0,
        mmOffset: Int = 0
    ): Date {
        return Date(
            yyyy - yyyyOffset,
                MM - MMOffset,
                dd - ddOffset,
                hh - hhOffset,
                mm - mmOffset,
        )
    }

    fun minus(other: Date): TimeOffset {
        val yyyyOffset = 365 * 24 * 60 * (yyyy - other.yyyy)
        val MMOffset = 30 * 24 * 60 * (MM - other.MM)
        val ddOffset = 24 * 60 * (dd - other.dd)
        val hhOffset = 60 * (hh - other.hh)
        val mmOffset = (mm - other.mm) + yyyyOffset + MMOffset + ddOffset + hhOffset

        return TimeOffset(mmOffset / 60, mmOffset % 60)
    }

    fun moreThanDayAfter(startDate: Date): Boolean {
        return (this.minus(startDate).hours >= 24)
    }
}