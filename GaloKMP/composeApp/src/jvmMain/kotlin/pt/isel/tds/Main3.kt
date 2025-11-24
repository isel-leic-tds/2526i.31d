package pt.isel.tds

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.unit.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.*
import kotlinx.coroutines.*
import kotlinx.coroutines.launch
import kotlin.concurrent.thread
import kotlin.time.*

/**
 * Demonstrates the difference between using coroutines and threads
 * to perform concurrent updates to a shared variable.
 */
fun main() = application {
    Window(onCloseRequest = ::exitApplication, state = WindowState(size = DpSize(200.dp, 150.dp))) {
        val scope = rememberCoroutineScope()
        var counter by remember { mutableStateOf(0) }
        var tm by remember { mutableStateOf(Duration.ZERO) }
        Column {
            Text("counter= $counter in $tm")
            Button(onClick = {
                tm = measureTime {
                    repeat(500) {
                        scope.launch { counter++; delay(10); counter++ }
                        //thread { counter++; Thread.sleep(10); counter++ }
                    }
                }
            }) { Text("Start") }
        }
    }
}
