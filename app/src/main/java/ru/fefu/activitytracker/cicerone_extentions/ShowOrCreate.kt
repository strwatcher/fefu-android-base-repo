package ru.fefu.activitytracker.cicerone_extentions

import com.github.terrakok.cicerone.Command
import com.github.terrakok.cicerone.Screen

data class ShowOrCreate(
    val screen: Screen,
    val fragmentId: Int
    ): Command
