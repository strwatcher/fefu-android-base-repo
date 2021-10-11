package ru.fefu.activitytracker.screens.main

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class ActionsFragmentAdapter(fragment: Fragment): FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return if (position == Tabs.My.position) {
            MyActionsFragment.newInstance()
        } else {
            UsersActionsFragment.newInstance()
        }
    }
}