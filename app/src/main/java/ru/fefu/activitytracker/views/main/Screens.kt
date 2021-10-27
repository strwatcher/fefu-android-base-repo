package ru.fefu.activitytracker.views.main

import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.fefu.activitytracker.models.MyActivity
import ru.fefu.activitytracker.models.UserActivity
import ru.fefu.activitytracker.views.main.fragments.*

object Screens {
    fun activitiesScreen() = FragmentScreen { ActivitiesFragment.newInstance() }
    fun profileScreen() = FragmentScreen { ProfileFragment.newInstance() }
    fun activityInfoScreen(activity: MyActivity) = FragmentScreen { MyActivityInfoFragment.newInstance(activity) }
    fun userActivityInfoScreen(activity: UserActivity) = FragmentScreen {
        UserActivityInfoFragment.newInstance(activity)
    }
}