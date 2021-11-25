package ru.fefu.activitytracker.database

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.fefu.activitytracker.extensions.*
import ru.fefu.activitytracker.model.ActivityType
import ru.fefu.activitytracker.model.ListItems
import ru.fefu.activitytracker.model.MyActivity
import java.time.Duration
import java.time.LocalDateTime

@Entity(tableName = "my_activities")
data class Activity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val type: ActivityType,
    val coordinates: List<Pair<Double, Double>>,
    @ColumnInfo(name = "start_time") val startTime: LocalDateTime,
    @ColumnInfo(name = "finish_time") val finishTime: LocalDateTime,
) {

    @RequiresApi(Build.VERSION_CODES.O)
    fun toMyActivity(): MyActivity {
        return MyActivity(
            type.title,
            coordinates.getDistance().toFormattedDistance(),
            finishTime.toFinishDateOrTime(),
            Duration.between(startTime, finishTime).toFormattedDurationBetween(),
            startTime.toTime(),
            finishTime.toTime(),
        )
    }

}
