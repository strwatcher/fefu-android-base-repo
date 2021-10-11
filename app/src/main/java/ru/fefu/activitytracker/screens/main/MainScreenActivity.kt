package ru.fefu.activitytracker.screens.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.fefu.activitytracker.R
import ru.fefu.activitytracker.databinding.ActivityMainScreenBinding


const val ActionTag = "Actions"
const val ProfileTag = "Profile"

class MainScreenActivity: AppCompatActivity() {
    private lateinit var _binding: ActivityMainScreenBinding
    private lateinit var _currentTag: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainScreenBinding.inflate(layoutInflater)

        setContentView(_binding.root)

        if (savedInstanceState == null) {
            loadStartTab()
        }

        setupTabSwitches()
    }

    private fun loadStartTab() {
        supportFragmentManager.beginTransaction().apply {
            add(R.id.ContentView, ActionsFragment.newInstance(), ActionTag)
            _currentTag = ActionTag
            commit()
        }
    }

    private fun setupTabSwitches() {
        _binding.bottomNavBar.setOnItemSelectedListener {
            supportFragmentManager.beginTransaction().apply {
                val currentFragment = supportFragmentManager.findFragmentByTag(_currentTag)

                _currentTag =
                    if (it.itemId == R.id.actionTab)
                        ActionTag
                    else
                        ProfileTag

                val nextFragment = supportFragmentManager.findFragmentByTag(_currentTag)

                if (currentFragment != null) {
                    hide(currentFragment)
                }

                if (nextFragment != null) {
                    show(nextFragment)
                } else {
                    add(R.id.ContentView, ProfileFragment.newInstance(), _currentTag)
                }

                commit()
            }
            true
        }
    }
}

