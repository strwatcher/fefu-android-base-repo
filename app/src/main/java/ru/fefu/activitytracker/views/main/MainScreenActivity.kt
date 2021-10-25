package ru.fefu.activitytracker.views.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.fefu.activitytracker.App
import ru.fefu.activitytracker.R
import ru.fefu.activitytracker.cicerone_extentions.ExtendedAppNavigator
import ru.fefu.activitytracker.databinding.ActivityMainScreenBinding
import ru.fefu.activitytracker.views.main.adapters.BottomNavigationViewAdapter


open class MainScreenActivity: AppCompatActivity() {
    private lateinit var binding: ActivityMainScreenBinding

    private lateinit var bottomNavigationViewAdapter: BottomNavigationViewAdapter

    private val navigator by lazy { ExtendedAppNavigator(this, R.id.navControllerFragment) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val screens = listOf(
            ScreenItem (
                R.id.activitiesFragment,
                Screens.activitiesScreen(),
            ),
            ScreenItem(
                R.id.profileFragment,
                Screens.profileScreen(),
            )
        )

        App.INSTANCE.router.newRootScreen(screens[0].screen)


        bottomNavigationViewAdapter = BottomNavigationViewAdapter(
            binding.bottomNavBar,
            screens
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

