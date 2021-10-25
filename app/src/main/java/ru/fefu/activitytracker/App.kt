package ru.fefu.activitytracker

import android.app.Application
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router

class App: Application() {

    companion object {
        lateinit var INSTANCE: App
    }

    private val cicerone = Cicerone.create()

    val router: Router
        get() = cicerone.router

    val navigatorHolder: NavigatorHolder
        get() = cicerone.getNavigatorHolder()

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
    }



}
