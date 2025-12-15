package pt.isel.tds.ttt.ui

import androidx.compose.runtime.*
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.window.*
import org.jetbrains.compose.resources.painterResource
import galokmp.composeapp.generated.resources.*

//var onExit: ()->Unit = { }

fun main() = application(exitProcessOnExit = false) {
    val onExit = remember { mutableStateOf<()->Unit>(::exitApplication) }
    Window(
        onCloseRequest = { onExit.value() },
        title = "GaloKMP",
        icon = painterResource(Res.drawable.cross),
        state = WindowState(size = DpSize.Unspecified)
    ) {
        AppGalo(onExit)
    }
}

