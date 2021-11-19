package ru.fefu.activitytracker

import android.app.Application
import androidx.room.Room
import ru.fefu.activitytracker.database.ActivitiesDataBase

class App: Application() {

    companion object {
        lateinit var INSTANCE: App
    }

    val database: ActivitiesDataBase by lazy {
        Room
            .databaseBuilder(
            this,
            ActivitiesDataBase::class.java,
            "activities_database"
            )
            .allowMainThreadQueries()
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
    }



}
