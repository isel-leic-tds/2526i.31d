package pt.isel.tds.ttt.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.desktop.ui.tooling.preview.Preview
import pt.isel.tds.ttt.model.*


@Composable
@Preview
fun AppGalo1() {
    var player by remember { mutableStateOf<Player?>(null) }
    MaterialTheme {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Player(player, onClick = {} )
            Button(onClick = {
                player = player?.other ?: Player.CROSS }) {
                Text("Change player")
            }
        }
    }
}