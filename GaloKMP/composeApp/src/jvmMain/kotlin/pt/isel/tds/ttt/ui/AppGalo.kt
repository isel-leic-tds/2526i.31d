package pt.isel.tds.ttt.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.*

@Composable
fun FrameWindowScope.AppGalo(onExit: ()->Unit) {
    val vm = remember { AppViewModel() }
    MenuBar {
        Menu("Game") {
            Item("start clash", onClick = vm::startClash)
            Item("join clash", onClick = vm::joinClash)
            Item("refresh", enabled = vm.isRun, onClick = vm::refresh)
            Item("new game", enabled = vm.isRun, onClick = vm::newBoard)
            Item("score", enabled = vm.isRun, onClick = vm::showScore)
            Item("exit", onClick = onExit)
        }
    }
    MaterialTheme {
        if (vm.isRun) Column {
            Grid(vm.game.board, onClick = vm::play)
            StatusBar(vm.game.state, vm.you)
        } else
            Box(Modifier.width(GRID_SIDE).height(GRID_SIDE + BAR_HEIGHT))
        if (vm.viewScore) ScoreInfo(vm.game.score, vm.name, vm::hideScore)
        vm.editMode?.let{ EditName(it, vm::cancelEdit, vm::doAction) }
        vm.message?.let { MessageDialog(it, vm::clearMessage) }
    }
}

