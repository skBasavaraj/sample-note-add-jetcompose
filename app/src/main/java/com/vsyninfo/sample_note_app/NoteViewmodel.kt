package com.vsyninfo.sample_note_app

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vsyninfo.sample_note_app.model.Note
import com.vsyninfo.sample_note_app.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class NoteViewModel @Inject constructor(private  val repository: NoteRepository):ViewModel() {
  private val _noteList = MutableStateFlow<List<Note>>(emptyList())
    val noteList = _noteList.asStateFlow()
    init {
        viewModelScope.launch (Dispatchers.IO){
            repository.getNotes().distinctUntilChanged().collectLatest {
                listOfNote->
                if(listOfNote.isNullOrEmpty()){
                    Log.d("TAG", "Empty list of notes")
                }
                else
                {
                  _noteList.value = listOfNote
                }
            }


        }
    }
// val noteList = mutableStateListOf<Note>()
     fun addNote(note: Note) =  viewModelScope.launch {
      repository.addNote(note)
}
//  {
//        //noteList.add(note)
//    }
     fun removeNote(note: Note) = viewModelScope.launch {
        repository.deleteNote(note)
}
//    {
//       // noteList.remove(note)
//    }
//    fun getNote():List<Note>{
//        //return  noteList
//    }
     fun  updateNote(note:Note ) = viewModelScope.launch {
       repository.updateNote(note)
}
}