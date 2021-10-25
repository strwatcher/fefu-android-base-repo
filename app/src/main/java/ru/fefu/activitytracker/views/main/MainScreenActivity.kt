package ru.fefu.activitytracker.views.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.terrakok.cicerone.androidx.AppNavigator
import ru.fefu.activitytracker.App
import ru.fefu.activitytracker.R
import ru.fefu.activitytracker.databinding.ActivityMainScreenBinding
import ru.fefu.activitytracker.views.main.adapters.BottomNavigationViewAdapter
import ru.fefu.activitytracker.views.main.fragments.ActivitiesFragment
import ru.fefu.activitytracker.views.main.fragments.ProfileFragment


class MainScreenActivity: AppCompatActivity() {
    private lateinit var binding: ActivityMainScreenBinding

    private lateinit var bottomNavigationViewAdapter: BottomNavigationViewAdapter

    private val navigator by lazy { AppNavigator(this, R.id.navControllerFragment) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction().apply {
            add(
                R.id.navControllerFragment,
                ActivitiesFragment.newInstance(),
                ActivitiesFragment.TAG
            )
            commit()
        }

        bottomNavigationViewAdapter = BottomNavigationViewAdapter(
            binding.bottomNavBar,
            supportFragmentManager,
            listOf(
                BottomNavigationViewAdapter.FragmentItem(
                    R.id.activitiesFragment,
                    ActivitiesFragment.TAG
                ) { ActivitiesFragment.newInstance() },

                BottomNavigationViewAdapter.FragmentItem(
                    R.id.profileFragment,
                    ProfileFragment.TAG,
                ) { ProfileFragment.newInstance() }
            ),
            R.id.navControllerFragment
        )
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        App.INSTANCE.navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        App.INSTANCE.navigatorHolder.removeNavigator()
        super.onPause()
    }

    override fun onBackPressed() {
        App.INSTANCE.router.exit()
    }
}

