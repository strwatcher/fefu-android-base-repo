package ru.fefu.activitytracker

import android.app.Application

class App: Application() {

    companion object {
        lateinit var INSTANCE: App
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
    }



}
