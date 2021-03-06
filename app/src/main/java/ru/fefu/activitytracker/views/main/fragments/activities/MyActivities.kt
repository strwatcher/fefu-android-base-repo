package ru.fefu.activitytracker.views.main.fragments.activities

import android.graphics.BitmapFactory
import android.graphics.drawable.Icon
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import ru.fefu.activitytracker.App
import ru.fefu.activitytracker.BaseFragment
import ru.fefu.activitytracker.R
import ru.fefu.activitytracker.database.Activity
import ru.fefu.activitytracker.databinding.FragmentMyActivitiesBinding
import ru.fefu.activitytracker.extensions.toDateSeparator
import ru.fefu.activitytracker.model.DateSeparator
import ru.fefu.activitytracker.model.IActivity
import ru.fefu.activitytracker.model.IListItem
import ru.fefu.activitytracker.model.MyActivity
import ru.fefu.activitytracker.views.main.adapters.ActivitiesViewAdapter

class MyActivities:
    BaseFragment<FragmentMyActivitiesBinding>(R.layout.fragment_my_activities) {

    private val _adapter = ActivitiesViewAdapter()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding.lvActivities) {
            adapter = _adapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        App.INSTANCE.database.activityDao().getAll().observe(viewLifecycleOwner) {
            activitiesList ->
            val packedList = packActivities(activitiesList)

            _adapter.submitList(packedList)

            if (packedList.isEmpty()) onWelcomeViews() else offWelcomeViews()

        }

        _adapter.setMyItemClickListener(::myActivityClickListener)

        switchButtonBehaviour()


    }

    override fun onResume() {
        super.onResume()
        switchButtonBehaviour()
    }

    private fun switchButtonBehaviour() {
        val lastActivity = App.INSTANCE.database.activityDao().getLast()

        lastActivity?.let {
            lastActivity.finishTime?.let {
                binding.bStartNewActivity.setImageDrawable(
                    ContextCompat.getDrawable(requireContext(), R.drawable.ic_triangle)
                )
                binding.bStartNewActivity.setOnClickListener(::newActivityStarterListener)
            } ?: run {
                binding.bStartNewActivity.setImageDrawable(
                    ContextCompat.getDrawable(requireContext(), R.drawable.ic_activity_started_timer)
                )
                binding.bStartNewActivity.setOnClickListener {
                    continueStartedActivity(lastActivity.id)
                }
            }
        } ?: run {
            binding.bStartNewActivity.setImageDrawable(
                ContextCompat.getDrawable(requireContext(), R.drawable.ic_triangle)
            )
            binding.bStartNewActivity.setOnClickListener(::newActivityStarterListener)
        }
    }

    private fun newActivityStarterListener(view: View) {
        println("aaa")
        val direction = ActivitiesDirections.actionActivitiesFragmentToActivityActivity()
        findNavController().navigate(direction)
    }

    private fun continueStartedActivity(activityId: Int) {
        println("bbb")
        val direction = ActivitiesDirections.actionActivitiesFragmentToActivityActivity(
            activityId = activityId
        )
        findNavController().navigate(direction)
    }

    private fun myActivityClickListener(position: Int, activityData: IActivity) {
        val direction =
            ActivitiesDirections.
            actionActivitiesFragmentToMyActivityInfo((activityData as MyActivity).id)

        findNavController().navigate(direction)
    }

    private fun packActivities(activitiesList: List<Activity>): List<IListItem> {
        val activitiesMap = mutableMapOf<String, MutableList<MyActivity>>()

        activitiesList.forEach {
            it.finishTime?.let { finishTime ->
                if (!activitiesMap.containsKey(finishTime.toDateSeparator())) {
                    activitiesMap[finishTime.toDateSeparator()] = mutableListOf()
                }

                activitiesMap[finishTime.toDateSeparator()]?.add(it.toMyActivity())
            }
        }

        val packedList = mutableListOf<IListItem>()

        activitiesMap.forEach { (dateSeparatorContent, myActivitiesList) ->
            packedList.add(DateSeparator(dateSeparatorContent))
            myActivitiesList.forEach {
                packedList.add(it)
            }
        }

        return packedList
    }

    private fun offWelcomeViews() {
        binding.tvHeader.visibility = View.INVISIBLE
        binding.tvSubHeader.visibility = View.INVISIBLE
    }

    private fun onWelcomeViews() {
        binding.tvHeader.visibility = View.VISIBLE
        binding.tvSubHeader.visibility = View.VISIBLE
    }
}