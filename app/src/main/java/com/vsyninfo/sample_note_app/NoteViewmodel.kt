package com.vsyninfo.sample_note_app

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import com.vsyninfo.sample_note_app.model.Note

class NoteViewModel:ViewModel() {
 val noteList = mutableStateListOf<Note>()
    fun addNote(note: Note){
        noteList.add(note)
    }
    fun removeNote(note: Note){
        noteList.remove(note)
    }
    fun getNote():List<Note>{
        return  noteList
    }
}