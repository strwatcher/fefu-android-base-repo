package ru.fefu.activitytracker

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.fefu.activitytracker.databinding.ActivityActionsScreenBinding

const val ActionTag = "Actions"
const val ProfileTag = "Profile"

class ActionsScreenActivity: AppCompatActivity(){
    private lateinit var _binding: ActivityActionsScreenBinding
    private lateinit var _currentTag: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityActionsScreenBinding.inflate(layoutInflater)

        setContentView(_binding.root)

        if (savedInstanceState == null) {
            loadStartTab()
        }

        setupTabSwitches()
    }

    private fun loadStartTab() {
        supportFragmentManager.beginTransaction().apply {
            add(R.id.fcMainView, ActionsFragment.newInstance(), ActionTag)
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
                }
                else {
                    add(R.id.fcMainView, ProfileFragment.newInstance(), _currentTag)
                }

                commit()
            }
            true
        }
    }
}
