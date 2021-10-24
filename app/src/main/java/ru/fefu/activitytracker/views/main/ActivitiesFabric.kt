package ru.fefu.activitytracker.views.main

import ru.fefu.activitytracker.models.*
import ru.fefu.activitytracker.models.Date


class ActivitiesFabric {

    private val metrics = listOf(
        "2 км 10 м",
        "123 м",
        "10 км",
        "5 км",
    )


    private val types = listOf(
        "Бег",
        "Велосипед",
        "Сёрфинг",
        "Сноуборд",
    )

    private val date = listOf(
        Pair(
            Date(2021, 10, 23, 12, 23),
            Date(2021, 10, 23, 13, 10)
        ),
        Pair(
            Date(2021, 5, 21, 13, 10),
            Date(2021, 5, 21, 15, 11)
        ),
        Pair(
            Date(2021, 7, 9, 23, 23),
            Date(2021, 7, 10, 14, 0)
        ),
        Pair(
            Date(2021, 10, 24, 11, 2),
            Date(2021, 10, 24, 11, 45)
        )
    )


    private fun genRandomActivity(): MyActivity {
        val dates = date.random()
        return MyActivity(
            types.random(),
            metrics.random(),
            dates.first,
            dates.second
        )
    }

    fun genActivities(count: Int): List<ListItemModel> {
        if (count < 1) return listOf()

        val activities = mutableListOf<MyActivity>()
        for(i in 1..count) {
            activities.add(genRandomActivity())
        }

        activities.sortBy { it.finishDate }

        var curDate = Date()
        val result = mutableListOf<ListItemModel>()
        for (i in 0 until count) {
            if ((curDate != activities[i].finishDate)) {
                curDate = activities[i].finishDate
                when {
                    activities[i].finishDate.equalsUpToDay(Date.now()) -> {
                        result.add(DateSeparator("Сегодня"))
                    }
                    activities[i].finishDate.equalsUpToDay(Date.now().minus(ddOffset = 1)) -> {
                        result.add(DateSeparator("Вчера"))
                    }
                    else -> {
                        result.add(DateSeparator(curDate.formattedDateSeparator))
                    }
                }
            }
            else if (!curDate.equalsUpToMonth(activities[i].finishDate)) {
                curDate = activities[i].finishDate
                result.add(DateSeparator(curDate.formattedDateSeparator))
            }
            result.add(activities[i])
        }

        return result
    }
}

fun main() {
    ActivitiesFabric().genActivities(10)
}