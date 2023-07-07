package com.vsyninfo.sample_note_app.screen

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.vsyninfo.sample_note_app.NoteViewModel
import com.vsyninfo.sample_note_app.model.Note

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun NoteScreen(
               getNote:  List<Note>,
               addNote:(Note) -> Unit,
               removeNote:(Note) -> Unit) {
  val context = LocalContext.current
    var title by remember {
        mutableStateOf("")
    }
    var description by remember {
        mutableStateOf("")
    }

    Scaffold(topBar = {
        TopAppBar(backgroundColor = Blue) {
            Text(text = "Note App", color = MaterialTheme.colors.background)

        }
    }) {

        Column(verticalArrangement = Arrangement.Top) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(horizontal = 20.dp, vertical = 10.dp)
            ) {

                NoteTextField(modifier = Modifier.fillMaxWidth(), text = title,
                    label = "Enter title", maxLine = 1, onTextChange = {
                        if (it.all { char -> char.isLetter() || char.isWhitespace() }) title = it
                    }
                )

                NoteTextField(modifier = Modifier.fillMaxWidth(), text = description,
                    label = "Write Something", maxLine = 1, onTextChange = {
                        if (it.all { char -> char.isLetter() || char.isWhitespace() }) description =
                            it
                    }
                )
                NoteButton(Modifier.fillMaxWidth(), "Add", onClick = {
                    Toast.makeText(context, "Added", Toast.LENGTH_SHORT).show()

                   // noteList.add(Note(title = title, description = description))
                    // Log.d("TAG","${noteList.toString()}")
                    addNote(Note(title = title, description = description,))
                    title =""
                    description =""
                })

            }

            LazyColumn {
                items(getNote) { it ->
                    Card(onClick = {
                        removeNote(it)
                    }, shape = RoundedCornerShape(
                         bottomStart = 10.dp,
                        topEnd = 10.dp

                    ), elevation = 5.dp,
                        backgroundColor = Color(200, 207, 243, 255),
                        border = BorderStroke(1.dp, color = Color(163, 161, 161, 255)),
                        modifier = Modifier.fillMaxWidth().padding(vertical = 5.dp, horizontal = 20.dp)
                    ) {
                        Column(
                            modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp)

                        ) {

                            Text(
                                text = it.title,
                                fontSize = TextUnit.Unspecified,
                                color = Black,
                                // modifer = Modifier.padding(vertical = 2.dp, horizontal = 20.dp),
                                fontWeight = FontWeight.W400
                            )
                            Text(
                                text = it.description,
                                fontSize = TextUnit.Unspecified,
                                color = Black,
                                fontWeight = FontWeight.W400
                            )
                        }
                    }

                }
            }
        }
    }
}




@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun NoteTextField(
    modifier: Modifier = Modifier,
    text: String,
    label: String,
    maxLine: Int,
    onTextChange: (String) -> Unit,
    onImeAction: () -> Unit = {}
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    TextField(
        value = text, onValueChange = onTextChange,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Transparent
        ),
        maxLines = maxLine, label = { Text(text = label) },
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(onDone = {
            onImeAction()
            keyboardController?.hide()
        }),
        modifier = modifier
    )
}

@Composable
fun NoteButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    enabled: Boolean = true
) {
    Button(
        onClick = onClick,
        shape = CircleShape,
        enabled = enabled,
        modifier = modifier
    ) {
        Text(text)
    }
}



