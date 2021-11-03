package ru.fefu.activitytracker.views.main

import ru.fefu.activitytracker.models.*


class ActivitiesStorage {
    companion object {
        @JvmStatic
        fun getMyActivities(): List<IListItem> =
            listOf(
                DateSeparator("Сегодня"),
                MyActivity(
                    name = "Бег",
                    metric = "4 км 100 м",
                    finishDate = "12 часов назад",
                    duration = "12 минут 45 секунд",
                    startTime = "13:00",
                    finishTime = "13:13"
                ),

                DateSeparator("Вчера"),

                MyActivity(
                    name = "Сёрфинг",
                    metric = "1 км",
                    finishDate = "25.10.2021",
                    duration = "1 час 10 минут",
                    startTime = "14:20",
                    finishTime = "15:30"
                ),

                DateSeparator("Октябрь 2021 года"),

                MyActivity(
                    name = "Велосипед",
                    metric = "15 км",
                    finishDate = "12.10.2021",
                    duration = "2 часа 13 минут",
                    startTime = "09:10",
                    finishTime = "11:23"
                ),
            )


        @JvmStatic
        fun getUsersActivities(): List<IListItem> =
            listOf(
                DateSeparator("Сегодня"),
                UserActivity(
                    name = "Бег",
                    metric = "4 км 100 м",
                    finishDate = "12 часов назад",
                    duration = "12 минут 45 секунд",
                    startTime = "13:00",
                    finishTime = "13:13",
                    userName = "@ДимДимыч",
                    userComment = "Бежал, чтобы успеть на новую серию мультсериала"
                ),

                DateSeparator("Вчера"),

                UserActivity(
                    name = "Сёрфинг",
                    metric = "1 км",
                    finishDate = "25.10.2021",
                    duration = "1 час 10 минут",
                    startTime = "14:20",
                    finishTime = "15:30",
                    userName = "@kaneki-kun",
                    userComment = "Тоука я обязательно посчитаю, сколько будет 1000-7"
                ),

                DateSeparator("Октябрь 2021 года"),

                UserActivity(
                    name = "Велосипед",
                    metric = "15 км",
                    finishDate = "12.10.2021",
                    duration = "2 часа 13 минут",
                    startTime = "09:10",
                    finishTime = "11:23",
                    userName = "@egor_blinov",
                    userComment = "Скатался до аякса, чтобы выпить американо с лимоном и погореть на Майю"
                ),
            )
    }
}
