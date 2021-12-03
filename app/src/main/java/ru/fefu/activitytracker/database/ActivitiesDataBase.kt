package ru.fefu.activitytracker.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters


@Database(entities = [Activity::class], version = 1)
@TypeConverters(Converters::class)
abstract class ActivitiesDataBase: RoomDatabase() {
    abstract fun activityDao(): ActivityDao

}