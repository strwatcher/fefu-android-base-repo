package ru.fefu.activitytracker.screens.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.fefu.activitytracker.databinding.FragmentMyActivitiesBinding

class MyActivitiesFragment: Fragment() {
    private var _binding: FragmentMyActivitiesBinding? = null

    private val binding get() = _binding!!

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

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}
