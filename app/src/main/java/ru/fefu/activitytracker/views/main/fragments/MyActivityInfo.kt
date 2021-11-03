package ru.fefu.activitytracker.views.main.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import ru.fefu.activitytracker.BaseFragment
import ru.fefu.activitytracker.R
import ru.fefu.activitytracker.databinding.MyActivityCardInfoBinding
import ru.fefu.activitytracker.models.MyActivity

class MyActivityInfo:
    BaseFragment<MyActivityCardInfoBinding>(R.layout.my_activity_card_info) {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState).also {
            val data: MyActivity = requireArguments().get("Activity") as MyActivity

            binding.tbAction.title = data.name
            binding.tvMetric.text = data.metric
            binding.tvStartTimeValue.text = data.startTime
            binding.tvFinishTimeValue.text = data.finishTime
            binding.tvDate.text = data.finishDate

            binding.tbAction.setNavigationOnClickListener{
                findNavController().popBackStack()
            }

        }
    }
}