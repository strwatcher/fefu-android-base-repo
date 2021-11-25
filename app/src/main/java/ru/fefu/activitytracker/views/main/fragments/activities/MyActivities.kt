package ru.fefu.activitytracker.views.main.fragments.activities

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import ru.fefu.activitytracker.App
import ru.fefu.activitytracker.BaseFragment
import ru.fefu.activitytracker.R
import ru.fefu.activitytracker.databinding.FragmentMyActivitiesBinding
import ru.fefu.activitytracker.extensions.toDateSeparator
import ru.fefu.activitytracker.model.DateSeparator
import ru.fefu.activitytracker.model.IListItem
import ru.fefu.activitytracker.model.MyActivity
import ru.fefu.activitytracker.views.main.ActivitiesStorage
import ru.fefu.activitytracker.views.main.adapters.ActivitiesViewAdapter

class MyActivities:
    BaseFragment<FragmentMyActivitiesBinding>(R.layout.fragment_my_activities) {

    private val _adapter = ActivitiesViewAdapter()


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding.lvActivities) {
            adapter = _adapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        App.INSTANCE.database.activityDao().getAll().observe(viewLifecycleOwner) {
            activitiesList ->
            val activitiesMap = mutableMapOf<String, MutableList<MyActivity>>()

            activitiesList.forEach {
                if (!activitiesMap.containsKey(it.finishTime.toDateSeparator())) {
                    activitiesMap[it.finishTime.toDateSeparator()] = mutableListOf()
                }

                activitiesMap[it.finishTime.toDateSeparator()]?.add(it.toMyActivity())
            }

            val packedList = mutableListOf<IListItem>()

            activitiesMap.forEach { (dateSeparatorContent, myActivitiesList) ->
                packedList.add(DateSeparator(dateSeparatorContent))
                myActivitiesList.forEach {
                    packedList.add(it)
                }
            }

            _adapter.submitList(packedList)
        }

        _adapter.setMyItemClickListener {
            _, activityData ->
            run {
                val direction =
                    ActivitiesDirections.
                    actionActivitiesFragmentToMyActivityInfo(activityData as MyActivity)
                findNavController().navigate(direction)
            }
        }

        binding.bStartNewActivity.setOnClickListener {
            val direction = ActivitiesDirections.actionActivitiesFragmentToActivityActivity()
            findNavController().navigate(direction)
        }
    }
}