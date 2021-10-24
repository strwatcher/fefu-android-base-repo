package ru.fefu.activitytracker.views.main.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import ru.fefu.activitytracker.databinding.FragmentMyActivitiesBinding
import ru.fefu.activitytracker.models.ListItemModel
import ru.fefu.activitytracker.models.MyActivity
import ru.fefu.activitytracker.views.main.adapters.ActivitiesViewAdapter
import ru.fefu.activitytracker.views.main.ActivitiesFabric
import java.util.*

class MyActivitiesFragment: Fragment() {
    private var _binding: FragmentMyActivitiesBinding? = null

    private val binding get() = _binding!!


    private var activities: List<ListItemModel> = ActivitiesFabric().genActivities(10)

    private val activitiesAdapter = ActivitiesViewAdapter(activities)

    companion object {
        fun newInstance(): MyActivitiesFragment {
            return MyActivitiesFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMyActivitiesBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding.activitiesListView) {
            adapter = activitiesAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}