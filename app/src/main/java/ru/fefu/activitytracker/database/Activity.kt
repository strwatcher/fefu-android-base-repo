package ru.fefu.activitytracker.database

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.fefu.activitytracker.extensions.getDistance
import ru.fefu.activitytracker.extensions.toFormattedDate
import ru.fefu.activitytracker.extensions.toFormattedDistance
import ru.fefu.activitytracker.model.ActivityType
import ru.fefu.activitytracker.model.MyActivity
import java.time.LocalDateTime

@Entity(tableName = "my_activities")
data class Activity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val type: ActivityType,
    val coordinates: List<Pair<Double, Double>>,
    @ColumnInfo(name = "start_time") val startTime: LocalDateTime,
    @ColumnInfo(name = "finish_time") val finishTime: LocalDateTime,
) {

}
