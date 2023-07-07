package com.vsyninfo.sample_note_app.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.vsyninfo.sample_note_app.model.Note
import com.vsyninfo.sample_note_app.util.DateConverter
import com.vsyninfo.sample_note_app.util.UUIDConverter

@Database(entities = [Note::class],version = 1, exportSchema = false)
@TypeConverters(DateConverter::class,UUIDConverter::class)
abstract  class NoteDatabase:RoomDatabase() {
    abstract  fun noteDao():NoteDatabaseDao
}