package pt.isel.tds

import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.desktop.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    print(" App")
    var text by remember { mutableStateOf("Hello World!") }
    MaterialTheme {
        Button(onClick = {
            println("\nClicked ")
            text = "Hello TDS!"
        }) {
            print(" Button ")
            Text(text)
        }
    }
}