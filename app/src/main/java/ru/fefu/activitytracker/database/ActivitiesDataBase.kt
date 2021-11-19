package ru.fefu.activitytracker.database

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [Activity::class], version = 1)
abstract class ActivitiesDataBase: RoomDatabase() {
    abstract fun activityDao(): ActivityDao

}