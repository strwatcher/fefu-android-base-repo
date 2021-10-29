package ru.fefu.activitytracker.views.main.fragments

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.github.terrakok.cicerone.Screen
import ru.fefu.activitytracker.App
import ru.fefu.activitytracker.R
import ru.fefu.activitytracker.databinding.MyActivityCardInfoBinding
import ru.fefu.activitytracker.models.IActivity

open class MyActivityInfoFragment(
    private val activityData: IActivity,
    ): Fragment() {
    private var _binding: MyActivityCardInfoBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance(activityData: IActivity): MyActivityInfoFragment {
            return MyActivityInfoFragment(activityData)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MyActivityCardInfoBinding.inflate(layoutInflater)
        (activity as AppCompatActivity).setSupportActionBar(binding.tbAction)
        setHasOptionsMenu(true)
        binding.tvMetric.text = activityData.metric
        binding.tbAction.title = activityData.name
        binding.tvDate.text = activityData.finishDate

        binding.tvStartTimeValue.text = activityData.startTime
        binding.tvFinishTimeValue.text = activityData.finishTime

        binding.tbAction.setNavigationOnClickListener {
            App.INSTANCE.router.Back()
        }

        return binding.root
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.my_activity_info_action_bar, menu)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}