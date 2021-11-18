package ru.fefu.activitytracker.views.activity.fragments

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import ru.fefu.activitytracker.BaseFragment
import ru.fefu.activitytracker.R
import ru.fefu.activitytracker.databinding.FragmentActivityStarterBinding
import ru.fefu.activitytracker.models.ActivityType
import ru.fefu.activitytracker.views.activity.ActivitiesTypesViewAdapter

class ActivityStarter:
    BaseFragment<FragmentActivityStarterBinding>(R.layout.fragment_activity_starter)
{
    private val _adapter = ActivitiesTypesViewAdapter(listOf(
        ActivityType("Бег"),
        ActivityType("Ходьба"),
        ActivityType("Велосипед"),
    ))

    private var selectedActivity: ActivityType? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        with(binding.recyclerView) {
            adapter = _adapter
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.HORIZONTAL,
                false
            )
        }

        _adapter.setItemClickListener {
            position, activityType ->
            selectedActivity = activityType
            for(i in 0..binding.recyclerView.layoutManager?.itemCount!!) {
                binding.recyclerView.layoutManager?.findViewByPosition(i)?.isSelected=false
            }
            binding.recyclerView.layoutManager?.findViewByPosition(position)?.isSelected=true
        }

        binding.bStart.setOnClickListener {
            selectedActivity?.let {
                val direction = ActivityStarterDirections
                    .actionActivityStarterToActivityActive(selectedActivity!!)
                findNavController().navigate(direction)
            }
        }
    }
}