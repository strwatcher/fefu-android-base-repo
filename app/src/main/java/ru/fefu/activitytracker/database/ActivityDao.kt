package ru.fefu.activitytracker.database

import androidx.lifecycle.LiveData
import androidx.room.*
import ru.fefu.activitytracker.model.ActivityType
import java.sql.RowId

@Dao
interface ActivityDao {

    @Query("select * from my_activities order by finish_time desc")
    fun getAll(): LiveData<List<Activity>>

    @Query("select * from my_activities where id=:id")
    fun getById(id: Int): Activity

    @Query("select * from my_activities where id=:id")
    fun getLiveById(id: Int): LiveData<Activity>

    @Query("select * from my_activities where type=:type")
    fun getByType(type: ActivityType): List<Activity>

    @Query("delete from my_activities where id=:id")
    fun deleteById(id: Int)

    @Update(entity = Activity::class)
    fun updatePath(updateObj: ActivityPathUpdate)

    @Update(entity = Activity::class)
    fun updateFinishTime(updateObj: ActivityFinishTimeUpdate)

    @Insert
    fun insert(activity: Activity) : Long

    @Delete
    fun delete(activity: Activity)

}
