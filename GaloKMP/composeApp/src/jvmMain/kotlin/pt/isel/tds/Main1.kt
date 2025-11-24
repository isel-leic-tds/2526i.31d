package pt.isel.tds

import androidx.compose.material3.*
import androidx.compose.ui.window.*

fun log(lab: String) = println("$lab: ${Thread.currentThread().name}")

/**
 * A Compose Desktop application that logs the execution thread.
 */
fun main() {
    log("main")
    application(exitProcessOnExit = false) {
        log("application")
        Window(onCloseRequest = ::exitApplication) {
            Button(onClick = {
                log("onClick")
            }) { Text("Ok") }
        }
    }
    log("exit")
}
