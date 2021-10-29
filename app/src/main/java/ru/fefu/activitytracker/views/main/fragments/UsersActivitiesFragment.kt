package ru.fefu.activitytracker.views.main.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import ru.fefu.activitytracker.App
import ru.fefu.activitytracker.databinding.FragmentUsersActivitiesBinding
import ru.fefu.activitytracker.models.IListItem
import ru.fefu.activitytracker.models.UserActivity
import ru.fefu.activitytracker.views.main.Activities
import ru.fefu.activitytracker.views.main.Screens
import ru.fefu.activitytracker.views.main.adapters.ActivitiesViewAdapter

class UsersActivitiesFragment: Fragment() {
    private var _binding: FragmentUsersActivitiesBinding? = null

    private val binding get() = _binding!!


    private var activities: List<IListItem> = Activities.getUsersActivities()
    private val activitiesAdapter = ActivitiesViewAdapter(activities)

    companion object {
        fun newInstance(): UsersActivitiesFragment {
            return  UsersActivitiesFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUsersActivitiesBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with (binding.lvActivities) {
            adapter = activitiesAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        activitiesAdapter.setUserItemClickListener { _, iActivity ->
            App.INSTANCE.router.navigateTo(
                Screens.userActivityInfoScreen(iActivity as UserActivity)
            )
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }

}