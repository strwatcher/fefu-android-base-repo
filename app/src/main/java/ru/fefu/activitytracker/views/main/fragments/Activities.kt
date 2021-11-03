package ru.fefu.activitytracker.views.main.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayoutMediator
import ru.fefu.activitytracker.BaseFragment
import ru.fefu.activitytracker.R
import ru.fefu.activitytracker.databinding.FragmentActivitiesBinding
import ru.fefu.activitytracker.views.main.adapters.ViewPagerAdapter

class Activities: BaseFragment<FragmentActivitiesBinding>(R.layout.fragment_activities) {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)

        binding.viewPager.adapter = ViewPagerAdapter(childFragmentManager, lifecycle)

        TabLayoutMediator(binding.tlActivitiesTabs, binding.viewPager) {
            tab, position -> tab.text = when(position) {
                0 -> "Мои"
                1 -> "Пользователей"
                else -> ""
            }
        }.attach()



        return binding.root
    }
}