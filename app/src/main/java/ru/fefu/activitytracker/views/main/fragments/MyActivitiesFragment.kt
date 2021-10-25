package ru.fefu.activitytracker.views.main.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import ru.fefu.activitytracker.App
import ru.fefu.activitytracker.R
import ru.fefu.activitytracker.databinding.FragmentMyActivitiesBinding
import ru.fefu.activitytracker.models.IActivity
import ru.fefu.activitytracker.models.IListItem
import ru.fefu.activitytracker.models.MyActivity
import ru.fefu.activitytracker.views.main.adapters.ActivitiesViewAdapter
import ru.fefu.activitytracker.views.main.ActivitiesFabric
import ru.fefu.activitytracker.views.main.MainScreenActivity
import ru.fefu.activitytracker.views.main.Screens

class MyActivitiesFragment: Fragment() {
    private var _binding: FragmentMyActivitiesBinding? = null

    private val binding get() = _binding!!

    private val fabric = ActivitiesFabric()
    private var activities: List<IListItem> = fabric.genActivities(10, fabric::genRandomActivity)
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

        activitiesAdapter.setMyItemClickListener {
                _: Int, data: IActivity ->
            App.INSTANCE.router.navigateTo(Screens.activityInfoScreen(data as MyActivity))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}
