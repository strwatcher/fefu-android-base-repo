package ru.fefu.activitytracker.views.main.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import ru.fefu.activitytracker.App
import ru.fefu.activitytracker.databinding.MyActivityCardInfoBinding
import ru.fefu.activitytracker.models.MyActivity

class ActivityInfoFragment(private val activityData: MyActivity): Fragment() {
    private var _binding: MyActivityCardInfoBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance(activityData: MyActivity): ActivityInfoFragment {
            return ActivityInfoFragment(activityData)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MyActivityCardInfoBinding.inflate(layoutInflater)
        (activity as AppCompatActivity).setSupportActionBar(binding.actionBar)
        binding.tvMetric.text = activityData.metric
        binding.actionBar.title = activityData.name
        return binding.root
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}