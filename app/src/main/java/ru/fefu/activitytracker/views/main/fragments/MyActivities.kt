package ru.fefu.activitytracker.views.main.fragments

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import ru.fefu.activitytracker.BaseFragment
import ru.fefu.activitytracker.R
import ru.fefu.activitytracker.databinding.FragmentMyActivitiesBinding
import ru.fefu.activitytracker.models.MyActivity
import ru.fefu.activitytracker.views.main.ActivitiesStorage
import ru.fefu.activitytracker.views.main.adapters.ActivitiesViewAdapter

class MyActivities:
    BaseFragment<FragmentMyActivitiesBinding>(R.layout.fragment_my_activities) {
    private val activities = ActivitiesStorage.getMyActivities()

    private val _adapter = ActivitiesViewAdapter(activities)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding.lvActivities) {
            adapter = _adapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        _adapter.setMyItemClickListener {
            _, activityData ->
            run {
                val action =
                    ActivitiesDirections.
                    actionActivitiesFragmentToMyActivityInfo(activityData as MyActivity)
                findNavController().navigate(action)
            }
        }
    }
}