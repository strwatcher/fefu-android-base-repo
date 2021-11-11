package ru.fefu.activitytracker.views.activity.fragments

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import ru.fefu.activitytracker.BaseFragment
import ru.fefu.activitytracker.R
import ru.fefu.activitytracker.databinding.FragmentActivityStarterBinding

class ActivityStarter:
    BaseFragment<FragmentActivityStarterBinding>(R.layout.fragment_activity_starter)
{
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.bStart.setOnClickListener{
            val direction = ActivityStarterDirections.actionActivityStarterToActivityActive()
            findNavController().navigate(direction)
        }
    }
}