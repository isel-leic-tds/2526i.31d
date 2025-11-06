package pt.isel.tds

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() {
    application(false) {
        print("\n main ")
        Window(
            onCloseRequest = ::exitApplication,
            title = "GaloKMP",
        ) {
            App()
        }
    }
    println("Bye.")
}