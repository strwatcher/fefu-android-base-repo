package ru.fefu.activitytracker.screens.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.fefu.activitytracker.R
import ru.fefu.activitytracker.databinding.ActivityMainScreenBinding


class MainScreenActivity: AppCompatActivity() {
    private lateinit var _binding: ActivityMainScreenBinding
    private lateinit var _navBarHandler: NavigationBarHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainScreenBinding.inflate(layoutInflater)
        setContentView(_binding.root)

        setupStartActivity(savedInstanceState)
        setupNavBarHandler()

    }

    private fun setupNavBarHandler() {
        _navBarHandler = NavigationBarHandler(
            _binding.bottomNavBar,
            supportFragmentManager,
            listOf(
                NavigationBarHandler.FragmentItem(
                    R.id.actionTab,
                    ActionsFragment.TAG,
                    ActionsFragment::newInstance
                ),
                NavigationBarHandler.FragmentItem(
                    R.id.profileTab,
                    ProfileFragment.TAG,
                    ProfileFragment::newInstance
                )
            ),
            R.id.contentView
        )
    }

    private fun setupStartActivity(savedInstanceState: Bundle?) {
        savedInstanceState?.let {} ?: supportFragmentManager.beginTransaction().apply {
            add(
                R.id.contentView,
                ActionsFragment.newInstance(),
                ActionsFragment.TAG
            )
            commit()
        }
    }

}

