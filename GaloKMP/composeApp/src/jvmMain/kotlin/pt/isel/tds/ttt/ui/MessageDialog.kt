package pt.isel.tds.ttt.ui

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier

@Composable
@Preview
fun MessageDialogTest() {
    MessageDialog("Error message", {})
}

@Composable
fun MessageDialog(msg: String, onOk: ()->Unit) {
    AlertDialog(
        onDismissRequest = onOk,
        title = { Text(msg) },
        modifier = Modifier.fillMaxWidth(0.6f),
        confirmButton = { Button(onClick=onOk ){ Text("Ok") } },
    )
}