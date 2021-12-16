package ru.fefu.activitytracker.extensions

import android.location.Location
import java.lang.IllegalStateException

fun List<Pair<Double, Double>>.getDistance(): Double {

    var result = 0.0f

    for (i in 1 until size) {
        val location1 = locationOfCoordinates(get(i - 1).first, get(i - 1).second)
        val location2 = locationOfCoordinates(get(i).first, get(i).second)
        val distance = location1.distanceTo(location2)
        result += distance
    }

    return result.toDouble()
}

fun Pair<Double, Double>.distanceTo(other: Pair<Double, Double>): Double {
    val location1 = locationOfCoordinates(first, second)
    val location2 = locationOfCoordinates(other.first, other.second)

    return location1.distanceTo(location2).toDouble()
}

fun Double.toFormattedDistance(): String {

    val ceilDistance = toInt()
    val km = ceilDistance / 1000
    val m = ceilDistance % 1000

    return when {
        km > 0 && m > 0 -> "$km км $m м"
        km > 0 -> "$km км"
        m >= 0 -> "$m м"
        else -> throw(IllegalStateException("Distance can't be negative"))
    }

}

fun locationOfCoordinates(longitude: Double, latitude: Double): Location {
    val location = Location("something")
    location.longitude = longitude
    location.latitude= latitude

    return location
}