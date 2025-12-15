package pt.isel.tds.ttt.ui

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import galokmp.composeapp.generated.resources.*
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.painterResource
import pt.isel.tds.ttt.model.Player

@Composable
@Preview
fun CellTest() {
    Column {
        Cell(Player.CROSS, Modifier.size(100.dp)) { }
        Cell(modifier = Modifier.background(Color.Cyan).size(150.dp)) { }
        Cell(Player.BALL) { }
    }
}

@Composable
fun Cell(
    player: Player? = null,
    modifier: Modifier = Modifier.size(200.dp),
    anim: Boolean = false,
    onClick: ()->Unit = {},
) {
    if (player==null) Box(modifier.clickable(onClick = onClick))
    else {
        val resource = when (player) {
            Player.CROSS -> Res.drawable.cross
            Player.BALL -> Res.drawable.circle
        }
        if (!anim)
            Image(painterResource(resource), "player", modifier = modifier)
        else {
            var zoom by remember { mutableStateOf(0.1f) }
            Image(
                painterResource(resource), "player",
                modifier = modifier.graphicsLayer(
                    scaleX = zoom, scaleY = zoom
                )
            )
            LaunchedEffect(player) {
                while (zoom < 1f) {
                    delay(50)
                    zoom += 0.1f
                }
            }
        }
    }
}