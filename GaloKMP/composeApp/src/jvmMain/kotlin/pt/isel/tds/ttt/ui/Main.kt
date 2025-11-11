package pt.isel.tds.ttt.ui

import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.window.*
import org.jetbrains.compose.resources.painterResource
import galokmp.composeapp.generated.resources.*

fun main() = application(false) {
        Window(
            onCloseRequest = ::exitApplication,
            title = "GaloKMP",
            icon = painterResource(Res.drawable.cross),
            state = WindowState(size = DpSize.Unspecified)
        ) {
            println("App")
            AppGalo()
        }
    }
