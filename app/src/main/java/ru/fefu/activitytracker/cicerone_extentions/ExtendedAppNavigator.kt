package ru.fefu.activitytracker.cicerone_extentions

import androidx.fragment.app.FragmentActivity
import com.github.terrakok.cicerone.Command
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.github.terrakok.cicerone.androidx.FragmentScreen

open class ExtendedAppNavigator (
    activity: FragmentActivity,
    containerId: Int
): AppNavigator(activity, containerId) {

    override fun applyCommand(command: Command) {
        super.applyCommand(command)
        when (command) {
            is ShowOrCreate -> showOrCreate(command)
            is Hide -> hide(command)
        }
    }

    protected open fun showOrCreate(command: ShowOrCreate) {
        when (val screen = command.screen) {
            is FragmentScreen -> {
                showHiddenOrCreateScreen(screen)
            }
        }
    }

    protected open fun hide(command: Hide) {
        when (val screen = command.screen) {
            is FragmentScreen -> {
                hideScreen(screen)
            }
        }
    }

    protected open fun showHiddenOrCreateScreen(screen: FragmentScreen) {
        val fragment = fragmentManager.findFragmentByTag(screen.screenKey)
        val transaction = fragmentManager.beginTransaction()

        fragment?.let {
            transaction.show(fragment)
        }  ?: transaction.add(containerId, screen.createFragment(fragmentFactory), screen.screenKey)

        transaction.commit()
    }

    protected open fun hideScreen(screen: FragmentScreen) {
        val fragment = fragmentManager.findFragmentByTag(screen.screenKey)
        val transaction = fragmentManager.beginTransaction()

        fragment?.let {
            transaction.hide(fragment)
        }

        transaction.commit()
    }

}