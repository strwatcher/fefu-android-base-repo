package ru.fefu.activitytracker.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import ru.fefu.activitytracker.model.ActivityType

@Dao
interface ActivityDao {

    @Query("select * from my_activities")
    fun getAll(): List<Activity>

    @Query("select * from my_activities where id=:id")
    fun getById(id: Int): Activity

    @Query("select * from my_activities where type=:type")
    fun getByType(type: ActivityType): List<Activity>

    @Insert
    fun insert(activity: Activity)

    @Delete
    fun delete(activity: Activity)

}
