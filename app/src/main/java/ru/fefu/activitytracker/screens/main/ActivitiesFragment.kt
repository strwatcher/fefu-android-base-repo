package ru.fefu.activitytracker.screens.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import ru.fefu.activitytracker.R
import ru.fefu.activitytracker.databinding.FragmentActivitiesBinding



class ActivitiesFragment: Fragment() {
    private var _binding: FragmentActivitiesBinding? = null

    private val binding get() = _binding!!

    companion object {
        const val TAG = "Actions"

        fun newInstance(): ActivitiesFragment{
            val args = Bundle()
            val fragment = ActivitiesFragment()
            fragment.arguments = args

            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentActivitiesBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val adapter = ActionsFragmentAdapter(this)
        binding.viewPager.adapter = adapter
        TabLayoutMediator(binding.tlTabs, binding.viewPager) { tab, position ->
            tab.text =
                if(position == Tabs.My.position) getString(R.string.my_activities_tab_title)
                else getString(R.string.users_activities_tab_title)
        }.attach()
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}
