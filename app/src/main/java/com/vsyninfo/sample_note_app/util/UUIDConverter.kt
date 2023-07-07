package com.vsyninfo.sample_note_app.util

import androidx.room.TypeConverter
import java.util.UUID

class UUIDConverter {
    @TypeConverter
    fun formUUID(uuid:UUID):String?{
        return  uuid.toString()
    }
    @TypeConverter
    fun uuidFromString(string:String?):UUID?{
        return UUID.fromString(string)
    }
}