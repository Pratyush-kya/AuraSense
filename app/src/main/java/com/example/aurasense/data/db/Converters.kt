package com.example.aurasense.data.db

import androidx.room.TypeConverter
import java.util.Date

class Converters {
    @TypeConverter
    fun fromTaskStatus(value: TaskStatus): String = value.name
    @TypeConverter
    fun toTaskStatus(value: String): TaskStatus = TaskStatus.valueOf(value)

    @TypeConverter
    fun fromTimestamp(value: Long?): Date? = value?.let { Date(it) }
    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? = date?.time
}