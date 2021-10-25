package ru.fefu.activitytracker.views.main

import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.fefu.activitytracker.models.MyActivity
import ru.fefu.activitytracker.views.main.fragments.ActivitiesFragment
import ru.fefu.activitytracker.views.main.fragments.ActivityInfoFragment
import ru.fefu.activitytracker.views.main.fragments.MyActivitiesFragment
import ru.fefu.activitytracker.views.main.fragments.ProfileFragment

object Screens {
    fun activitiesScreen() = FragmentScreen { ActivitiesFragment.newInstance() }
    fun profileScreen() = FragmentScreen { ProfileFragment.newInstance() }
    fun activityInfoScreen(activity: MyActivity) = FragmentScreen { ActivityInfoFragment.newInstance(activity) }

}