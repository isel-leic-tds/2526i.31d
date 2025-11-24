package pt.isel.tds

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.window.*
import kotlinx.coroutines.*
import kotlinx.coroutines.launch
import kotlin.concurrent.thread

/**
 * Simple application to alternate the enable button.
 * The "Click me" button when clicked becomes disabled for 5 seconds
 * or until the "Enable Click" button is clicked.
 */
fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        val scope = rememberCoroutineScope()
        //var clickable by remember { mutableStateOf(true) }
        var job by remember { mutableStateOf<Job?>(null) }
        val clickable = job == null
        Row {
            Button(enabled = clickable, onClick = {
                println("Clicked")
                //clickable = false
                job = scope.launch{
                //thread {
                    repeat(5) { print('.'); delay(1000) }
                    //repeat(5) { print('.'); Thread.sleep(1000) }
                    //clickable = true
                    job = null
                }
            }) { Text("Click me") }
            Button(enabled = !clickable, onClick = {
                //clickable = true
                job?.cancel()
                job = null
            }) { Text("Enable Click") }
        }
    }
}
