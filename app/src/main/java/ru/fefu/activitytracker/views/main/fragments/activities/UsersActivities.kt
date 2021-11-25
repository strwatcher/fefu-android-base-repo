package ru.fefu.activitytracker.views.main.fragments.activities

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import ru.fefu.activitytracker.BaseFragment
import ru.fefu.activitytracker.R
import ru.fefu.activitytracker.databinding.FragmentUsersActivitiesBinding
import ru.fefu.activitytracker.model.UserActivity
import ru.fefu.activitytracker.views.main.ActivitiesStorage
import ru.fefu.activitytracker.views.main.adapters.ActivitiesViewAdapter

class UsersActivities:
    BaseFragment<FragmentUsersActivitiesBinding>(R.layout.fragment_users_activities) {
    private val activities = ActivitiesStorage.getUsersActivities()

    private val _adapter = ActivitiesViewAdapter()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding.lvActivities) {
            adapter = _adapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        _adapter.submitList(activities)

        _adapter.setUserItemClickListener {
                _, iActivity ->
            run {
                val direction = ActivitiesDirections.
                actionActivitiesFragmentToUserActivityInfo(iActivity as UserActivity)
                findNavController().navigate(direction)
            }
        }
    }
}