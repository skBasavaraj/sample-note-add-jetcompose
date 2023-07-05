package com.vsyninfo.sample_note_app

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import com.vsyninfo.sample_note_app.screen.Note

class NoteViewModel:ViewModel() {
    var noteList = mutableStateListOf<Note>()

    fun addNote(title:String,description:String){
        noteList.add(Note(title, description))
    }
}