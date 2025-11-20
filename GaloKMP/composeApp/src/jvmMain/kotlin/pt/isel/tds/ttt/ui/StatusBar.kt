package pt.isel.tds.ttt.ui

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import pt.isel.tds.ttt.model.*

val BAR_HEIGHT = 50.dp

@Composable
@Preview
fun StatusBarTest() {
    val status = Run(Player.CROSS)
    StatusBar(status, Player.BALL)
}

@Composable
fun LabeledCell(label: String, player: Player?) = Row {
    Text(label, style = MaterialTheme.typography.displaySmall)
    player?.let{ Cell(it, Modifier.padding(5.dp)) }
}

@Composable
fun StatusBar(state: GameState, you: Player) = Row(
    modifier = Modifier
        .background(Color.LightGray)
        .height(BAR_HEIGHT).width(GRID_SIDE),
    horizontalArrangement = Arrangement.Center,
    verticalAlignment = Alignment.CenterVertically
) {
    LabeledCell("You:", you)
    Spacer(Modifier.width(BAR_HEIGHT))
    val (label, player) = when (state) {
        is Run -> "Turn:" to state.turn
        is Win -> "Winner:" to state.winner
        is Draw -> "Draw" to null
    }
    LabeledCell(label,player)
}