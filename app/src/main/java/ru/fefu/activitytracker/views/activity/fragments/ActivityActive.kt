package ru.fefu.activitytracker.views.activity.fragments

import android.os.Bundle
import android.view.View
import ru.fefu.activitytracker.BaseFragment
import ru.fefu.activitytracker.R
import ru.fefu.activitytracker.databinding.FragmentActivityActiveBinding
import ru.fefu.activitytracker.model.ActivityType

class ActivityActive:
    BaseFragment<FragmentActivityActiveBinding>(R.layout.fragment_activity_active)
{
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val activityType = requireArguments()["ActivityType"] as ActivityType

        binding.tvActivityType.text = activityType.title

        super.onViewCreated(view, savedInstanceState)
    }


}