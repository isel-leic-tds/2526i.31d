package pt.isel.tds.ttt.ui

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import pt.isel.tds.ttt.model.Draw
import pt.isel.tds.ttt.model.GameState
import pt.isel.tds.ttt.model.Player
import pt.isel.tds.ttt.model.Run
import pt.isel.tds.ttt.model.Win

val BAR_HEIGHT = 50.dp

@Composable
@Preview
fun StatusBarTest() {
    val status = Run(Player.CROSS)
    StatusBar(status)
}

@Composable
fun LabeledCell(label: String, player: Player?) = Row {
    Text(label, style = MaterialTheme.typography.displayMedium)
    player?.let{ Cell(it) }
}

@Composable
fun StatusBar(state: GameState) = Row(
    modifier = Modifier
        .background(Color.LightGray)
        .height(BAR_HEIGHT).width(GRID_SIDE),
    horizontalArrangement = Arrangement.Center,
    verticalAlignment = Alignment.CenterVertically
) {
    val (label, player) = when (state) {
        is Run -> "Turn:" to state.turn
        is Win -> "Winner:" to state.winner
        is Draw -> "Draw" to null
    }
    LabeledCell(label,player)
}