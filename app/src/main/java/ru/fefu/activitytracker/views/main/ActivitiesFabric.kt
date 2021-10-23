package ru.fefu.activitytracker.views.main

import ru.fefu.activitytracker.models.*


class ActivitiesFabric {

    private val metrics = listOf(
        "2км 140м",
        "123м",
        "10км",
        "5км",
    )


    private val types = listOf(
        "Бег",
        "Велосипед",
        "Сёрфинг",
        "Сноуборд",
    )

    private val time = listOf(
        TimeOffset(1, 12),
        TimeOffset(2, 11),
        TimeOffset(0, 12)
    )

    private val date = listOf(
        DateTime(2021, 10, 23, 0, 0),
        DateTime(2021, 5, 21, 0, 0),
        DateTime(2021, 7, 10, 0, 0)
    )


    private fun genRandomActivity(): MyActivity {
        return MyActivity(
            types.random(),
            metrics.random(),
            time.random(),
            date.random()
        )
    }

    fun genActivities(count: Int): List<ListItemModel> {
        if (count < 1) return listOf()

        val activities = mutableListOf<MyActivity>()
        for(i in 1..count) {
            activities.add(genRandomActivity())
        }

        activities.sortBy { it.date }

        var curDate: DateTime? = null
        val result = mutableListOf<ListItemModel>()

        for (i in 0 until count) {
            if (curDate != activities[i].date) {
                curDate = activities[i].date
                result.add(DateSeparator(curDate))
            }
            result.add(activities[i])
        }

        return result
    }
}