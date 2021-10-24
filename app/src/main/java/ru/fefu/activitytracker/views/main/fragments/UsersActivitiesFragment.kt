package ru.fefu.activitytracker.views.main.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import ru.fefu.activitytracker.databinding.FragmentUsersActivitiesBinding
import ru.fefu.activitytracker.models.IListItem
import ru.fefu.activitytracker.views.main.ActivitiesFabric
import ru.fefu.activitytracker.views.main.adapters.ActivitiesViewAdapter

class UsersActivitiesFragment: Fragment() {
    private var _binding: FragmentUsersActivitiesBinding? = null

    private val binding get() = _binding!!

    private val fabric = ActivitiesFabric()
    private var activities: List<IListItem> = fabric.genActivities(10, fabric::genRandomUserActivity)
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

        with (binding.activitiesListView) {
            adapter = activitiesAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }

}