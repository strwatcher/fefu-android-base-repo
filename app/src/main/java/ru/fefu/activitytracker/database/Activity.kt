package ru.fefu.activitytracker.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.fefu.activitytracker.extensions.*
import ru.fefu.activitytracker.model.ActivityType
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
    fun toMyActivity(): MyActivity {
        return MyActivity(
            id,
            type.title,
            coordinates.getDistance().toFormattedDistance(),
            finishTime.toFinishDateOrTime(),
            Duration.between(startTime, finishTime).toFormattedDurationBetween(),
            startTime.toTime(),
            finishTime.toTime(),
        )
    }
}

data class ActivityPathUpdate(
    val id: Int,
    val coordinates: List<Pair<Double, Double>>,
)

data class ActivityFinishTimeUpdate(
    val id: Int,
    @ColumnInfo(name = "finish_time") val finishTime: LocalDateTime,
)