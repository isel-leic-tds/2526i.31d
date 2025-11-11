package pt.isel.tds.ttt.ui

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import pt.isel.tds.ttt.model.*

val CELL_SIDE = 200.dp
val LINE_THICKNESS = 5.dp
val GRID_SIDE = CELL_SIDE*BOARD_SIZE + LINE_THICKNESS*(BOARD_SIZE-1)

@Composable
@Preview
fun BoardTest() {
    Grid(mapOf(
        Position(0) to Player.CROSS,
        Position(4) to Player.BALL
    )) {}
}

@Composable
fun Grid(board: Board, onClick: (Position)->Unit) {
    Column(Modifier.height(GRID_SIDE).background(Color.Black), Arrangement.SpaceBetween) {
        repeat(BOARD_SIZE) { row ->
            Row(Modifier.width(GRID_SIDE), Arrangement.SpaceBetween) {
                repeat(BOARD_SIZE) { col ->
                    val position = Position(row * BOARD_SIZE + col)
                    Cell(
                        board[position],
                        Modifier.size(CELL_SIDE).background(Color.White),
                        onClick = { onClick(position) }
                    )
                }
            }
        }
    }
}

@Composable
@Preview
fun AppGalo() {
    var game by remember { mutableStateOf(Game()) }
    MaterialTheme {
        Grid(game.board, onClick = {
            if (game.state is Run) game = game.play(it)
        })
    }
}
