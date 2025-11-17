package pt.isel.tds.ttt.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.window.*

@Composable
fun FrameWindowScope.AppGalo(onExit: ()->Unit) {
    val vm = remember { AppViewModel() }
    MenuBar {
        Menu("Game") {
            Item("start clash", onClick = vm::startClash)
            Item("new game", onClick = vm::newBoard)
            Item("score", onClick = vm::showScore)
            Item("exit", onClick = onExit)
        }
    }
    MaterialTheme {
        Column {
            Grid(vm.game.board, onClick = vm::play)
            StatusBar(vm.game.state)
        }
        if (vm.viewScore) ScoreInfo(vm.game.score, vm::hideScore)
        if (vm.editMode!=null) EditName(vm.editMode, vm::modeAction)
    }
}

