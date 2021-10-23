package ru.fefu.activitytracker.views.main.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import ru.fefu.activitytracker.views.main.Tabs
import ru.fefu.activitytracker.views.main.fragments.MyActivitiesFragment
import ru.fefu.activitytracker.views.main.fragments.UsersActivitiesFragment
import java.util.*

class ActionsFragmentAdapter(fragment: Fragment): FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return if (position == Tabs.My.position) {
            MyActivitiesFragment.newInstance()
        } else {
            UsersActivitiesFragment.newInstance()
        }
    }
}