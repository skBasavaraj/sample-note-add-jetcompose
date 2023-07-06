package com.vsyninfo.sample_note_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.vsyninfo.sample_note_app.model.Note
import com.vsyninfo.sample_note_app.screen.NoteScreen
import com.vsyninfo.sample_note_app.ui.theme.SamplenoteappTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SamplenoteappTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                val noteViewModels : NoteViewModel by viewModels()
                    Notes(noteViewModels)
                }
            }
        }
    }

    @Composable
    fun Notes(viewModel: NoteViewModel =  viewModel()) {
        val noteList = viewModel.getNote()
        NoteScreen(getNote = noteList, addNote = {
            viewModel.addNote(it)
        }, removeNote = {
            viewModel.removeNote(it)
        })
    }
}



