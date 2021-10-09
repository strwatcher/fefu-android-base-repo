package ru.fefu.activitytracker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import ru.fefu.activitytracker.databinding.FragmentActionsBinding

enum class Tabs(val position: Int, val message: String) {
    My(0, "Мои активности"),
    Users(1, "Активности пользователей"),
}

class ActionsFragment: Fragment() {
    private lateinit var _binding: FragmentActionsBinding

    companion object {
        fun newInstance(): ActionsFragment{
            val args = Bundle()
            val fragment = ActionsFragment()
            fragment.arguments = args

            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentActionsBinding.inflate(layoutInflater)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val adapter = ActionsFragmentAdapter(this)
        _binding.viewPager.adapter = adapter
        TabLayoutMediator(_binding.tlTabs, _binding.viewPager) { tab, position ->
            tab.text =
                if(position == Tabs.My.position) getString(R.string.my_activities_tab_title)
                else getString(R.string.users_activities_tab_title)
        }.attach()
    }
}

class ActionsFragmentAdapter(fragment: Fragment): FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return MyActionsFragment.newInstance(
            if (position == Tabs.My.position)
                Tabs.My.message
            else
                Tabs.Users.message
        )
    }
}