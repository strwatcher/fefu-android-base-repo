package ru.fefu.activitytracker.views.activity.fragments

import android.os.Bundle
import android.view.View
import kotlinx.coroutines.*
import org.osmdroid.util.GeoPoint
import ru.fefu.activitytracker.App
import ru.fefu.activitytracker.BaseFragment
import ru.fefu.activitytracker.R
import ru.fefu.activitytracker.database.ActivityFinishTimeUpdate
import ru.fefu.activitytracker.databinding.FragmentActivityActiveBinding
import ru.fefu.activitytracker.extensions.toFormattedDistance
import ru.fefu.activitytracker.extensions.toTimerFormat
import ru.fefu.activitytracker.model.ActivityType
import ru.fefu.activitytracker.views.activity.ActivityActivity
import ru.fefu.activitytracker.views.activity.ActivityService
import java.time.Duration
import java.time.LocalDateTime

class ActivityActive:
    BaseFragment<FragmentActivityActiveBinding>(R.layout.fragment_activity_active)
{
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val activityType = requireArguments()["ActivityType"] as ActivityType
        val activityId = requireArguments()["ActivityId"] as Int

        ActivityService.startForegroundTracking(requireContext(), activityId)

        val activityData = App.INSTANCE.database.activityDao().getById(activityId)

        binding.tvActivityType.text = activityType.title

        App.INSTANCE.database.activityDao().getLiveById(activityId)
            .observe(viewLifecycleOwner) {
                if (it.coordinates.isNotEmpty()) {
                        val lastCoordinate = it.coordinates.last()
                    (activity as ActivityActivity).polyline.addPoint(
                        GeoPoint(lastCoordinate.first, lastCoordinate.second)
                    )
                }
            }

        val uiJob = GlobalScope.launch {
            withContext(Dispatchers.IO) {
                while (true) {
                    activity?.runOnUiThread {
                        binding.tvTimer.text =
                            Duration.between(activityData.startTime, LocalDateTime.now()).toTimerFormat()

                        binding.tvDistance.text = ActivityService.distance.toFormattedDistance()
                    }
                    delay(1000L)
                }
            }
        }

        binding.bFinish.setOnClickListener {
            ActivityService.stopForegroundTracking(activity!!, activityId)

            App.INSTANCE.database.activityDao().updateFinishTime(
                ActivityFinishTimeUpdate(activityId, LocalDateTime.now())
            )

            uiJob.cancel()
        }
        super.onViewCreated(view, savedInstanceState)
    }


}