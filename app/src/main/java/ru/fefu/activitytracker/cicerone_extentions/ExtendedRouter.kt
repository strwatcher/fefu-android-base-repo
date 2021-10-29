package ru.fefu.activitytracker.cicerone_extentions

import com.github.terrakok.cicerone.Back
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.Screen

open class ExtendedRouter: Router() {

    open fun showOrCreate(screen: Screen, fragmentId: Int) {
        executeCommands(ShowOrCreate(screen, fragmentId))
    }

    open fun hide(screen: Screen, fragmentId: Int) {
        executeCommands(Hide(screen, fragmentId))
    }

    open fun Back() {
        executeCommands(com.github.terrakok.cicerone.Back())
    }
}