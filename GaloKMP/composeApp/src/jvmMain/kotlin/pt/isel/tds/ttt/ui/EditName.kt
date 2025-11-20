package pt.isel.tds.ttt.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import pt.isel.tds.ttt.model.Name

@Composable
fun EditName(mode: EditMode, onCancel: ()->Unit, onAction: (Name)->Unit) {
    var name by remember { mutableStateOf("") }
    AlertDialog(
        onDismissRequest = onCancel,
        title = { Text("Name to ${mode.text}") },
        modifier = Modifier.fillMaxWidth(0.6f),
        confirmButton = { Button(onClick={ onAction(Name(name)) } ){ Text(mode.text) } },
        dismissButton = { Button(onClick=onCancel){ Text("Cancel") } },
        text = {
            OutlinedTextField(
                value = name,
                onValueChange = { if (Name.isValid(it)) name = it },
                label = { Text("Name of clash") }
            )
        },
    )
}