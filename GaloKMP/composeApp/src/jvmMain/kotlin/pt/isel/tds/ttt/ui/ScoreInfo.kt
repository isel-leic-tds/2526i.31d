package pt.isel.tds.ttt.ui

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.unit.dp
import pt.isel.tds.ttt.model.*

@Composable
fun ScoreInfo(score: Score, name: Name, onClose: ()-> Unit) = AlertDialog(
    onDismissRequest = onClose,
    title = { Text("Score of $name") },
    modifier = Modifier.fillMaxWidth(0.6f),
    confirmButton = { Button(onClick=onClose){ Text("Close") } },
    text = { Placard(score) },
)

@Composable
@Preview
fun PlacardTest() {
    Placard(Score())
}

@Composable
fun Placard(score: Score) {
    val style = MaterialTheme.typography.displaySmall
    Row(
        Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Player.entries.forEach {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Cell(it, Modifier.size(30.dp))
                    Text(" - ${score[it]}", style = style)
                }
            }
        }
        Text("Draws - ${score[null]}", style = style)
    }
}