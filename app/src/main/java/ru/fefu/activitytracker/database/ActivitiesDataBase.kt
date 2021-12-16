package ru.fefu.activitytracker.database

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters


@TypeConverters(Converters::class)
@Database(
    version = 2,
    entities = [Activity::class],
    autoMigrations = [AutoMigration (from = 1, to = 2)]
)
abstract class ActivitiesDataBase: RoomDatabase() {
    abstract fun activityDao(): ActivityDao

}
