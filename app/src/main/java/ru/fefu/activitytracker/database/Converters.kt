package ru.fefu.activitytracker.database

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.TypeConverter
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import java.time.LocalDateTime
import java.time.ZoneOffset

class Converters {

    @RequiresApi(Build.VERSION_CODES.O)
    @TypeConverter
    fun fromLocalDateTime(value: LocalDateTime?): Long? {
        return value?.toEpochSecond(ZoneOffset.UTC)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @TypeConverter
    fun toLocalDateTime(value: Long?): LocalDateTime? {
        return value?.let { LocalDateTime.ofEpochSecond(it, 0, ZoneOffset.UTC) }
    }

    @TypeConverter
    fun fromCoordinates(value: List<Pair<Double, Double>>?): String? {
        return value?.let {
            val gsonCoordinatesBuilder = GsonBuilder().create()
            val type = object: TypeToken<List<Pair<Double, Double>>>() {}.type
            gsonCoordinatesBuilder.toJson(value, type)
        }

    }

    @TypeConverter
    fun toCoordinates(value: String?): List<Pair<Double, Double>>? {
        return value?.let {
            val gsonCoordinatesBuilder = GsonBuilder().create()
            val type = object: TypeToken<List<Pair<Double, Double>>>() {}.type
            gsonCoordinatesBuilder.fromJson(it, type)
        }
    }


}