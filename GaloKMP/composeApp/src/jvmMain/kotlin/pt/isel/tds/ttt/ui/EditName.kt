package pt.isel.tds.ttt.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEvent
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.key.type
import pt.isel.tds.ttt.model.Name

@Composable
fun EditName(mode: EditMode, onCancel: ()->Unit, onAction: (Name)->Unit) {
    var name by remember { mutableStateOf("") }
    val isValid = Name.isValid(name)
    val fr = remember { FocusRequester() }
    fun keyHandler(ke: KeyEvent): Boolean =
        if (ke.key== Key.Enter && ke.type== KeyEventType.KeyDown) {
            if (isValid) onAction(Name(name))
            true
        } else false
    AlertDialog(
        onDismissRequest = onCancel,
        title = { Text("Name to ${mode.text}") },
        modifier = Modifier.fillMaxWidth(0.6f),
        confirmButton = {
            Button(
                enabled = isValid,
                onClick={ onAction(Name(name)) }
            ){ Text(mode.text) } },
        dismissButton = {
            Button(onClick=onCancel){ Text("Cancel") }
        },
        text = {
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Name of clash") },
                isError = !isValid,
                singleLine = true,
                modifier = Modifier
                    .onKeyEvent(::keyHandler)
                    .focusRequester(fr)
            )
        },
    )
    LaunchedEffect(Unit) { fr.requestFocus() }
}