package pt.isel.tds.ttt.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.window.*
import pt.isel.tds.ttt.model.*

@Composable
fun FrameWindowScope.AppGalo(onExit: ()->Unit) {
    var game by remember { mutableStateOf(Game()) }
    var viewScore by remember { mutableStateOf(false) }
    MenuBar {
        Menu("Game") {
            Item("new game") { game = game.new() }
            Item("score") { viewScore = true }
            Item("exit", onClick = onExit)
        }
    }
    MaterialTheme {
        Column {
            Grid(game.board, onClick = {
                if (game.state is Run) game = game.play(it)
            })
            StatusBar(game.state)
        }
        if (viewScore) ScoreInfo(game.score){ viewScore = false }
    }
}

