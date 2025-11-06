package pt.isel.tds.ttt.ui

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import galokmp.composeapp.generated.resources.*
import org.jetbrains.compose.resources.painterResource
import pt.isel.tds.ttt.model.Player

@Composable
@Preview
fun PlayerTest() {
    Column {
        Player(Player.CROSS, Modifier.size(100.dp)) { }
        Player(modifier = Modifier.background(Color.Cyan).size(150.dp)) { }
        Player(Player.BALL) { }
    }
}

@Composable
fun Player(
    player: Player? = null,
    modifier: Modifier = Modifier.size(200.dp),
    onClick: ()->Unit,
) {
    if (player==null) Box(modifier.clickable(onClick = onClick))
    else {
        val resource = when (player) {
            Player.CROSS -> Res.drawable.cross
            Player.BALL -> Res.drawable.circle
        }
        Image(painterResource(resource), "player", modifier = modifier)
    }
}