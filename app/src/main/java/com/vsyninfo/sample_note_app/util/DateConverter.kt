package com.vsyninfo.sample_note_app.util

import androidx.room.TypeConverter
import java.util.*

class DateConverter {
    @TypeConverter
    fun timestampForDate(date: Date):Long{
        return  date.time
    }
    @TypeConverter
    fun dateForTimeStamp(timeStamp: Long):Date?{
        return  Date(timeStamp)
    }
}