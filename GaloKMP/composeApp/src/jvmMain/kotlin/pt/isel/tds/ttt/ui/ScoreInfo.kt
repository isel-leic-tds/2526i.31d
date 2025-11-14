package pt.isel.tds.ttt.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.unit.dp
import pt.isel.tds.ttt.model.*

@Composable
fun ScoreInfo(score: Score, onClose: ()-> Unit) = AlertDialog(
    onDismissRequest = onClose,
    title = { Text("Score") },
    modifier = Modifier.fillMaxWidth(0.6f),
    confirmButton = { Button(onClick=onClose){ Text("Close") } },
    text = {
        val style = MaterialTheme.typography.displaySmall
        Row(Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Player.entries.forEach {
                    Row(verticalAlignment = Alignment.CenterVertically){
                        Cell(it, Modifier.size(30.dp))
                        Text(" - ${score[it]}", style = style)
                    }
                }
            }
            Text("Draws - ${score[null]}", style = style)
        }
    },
)