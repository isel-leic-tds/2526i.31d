package pt.isel.tds.ttt.ui

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import pt.isel.tds.ttt.model.BOARD_SIZE
import pt.isel.tds.ttt.model.Board
import pt.isel.tds.ttt.model.Player
import pt.isel.tds.ttt.model.Position

val CELL_SIDE = 200.dp
val LINE_THICKNESS = 5.dp
val GRID_SIDE = CELL_SIDE* BOARD_SIZE + LINE_THICKNESS*(BOARD_SIZE -1)

@Composable
@Preview
fun GridTest() {
    Grid(mapOf(
        Position.Companion(0) to Player.CROSS,
        Position.Companion(4) to Player.BALL
    )) {}
}

@Composable
fun Grid(board: Board, onClick: (Position)->Unit) {
    Column(Modifier.Companion.height(GRID_SIDE).background(Color.Companion.Black), Arrangement.SpaceBetween) {
        repeat(BOARD_SIZE) { row ->
            Row(Modifier.Companion.width(GRID_SIDE), Arrangement.SpaceBetween) {
                repeat(BOARD_SIZE) { col ->
                    val position = Position.Companion(row * BOARD_SIZE + col)
                    Cell(
                        board[position],
                        Modifier.Companion.size(CELL_SIDE).background(Color.Companion.White),
                        onClick = { onClick(position) }
                    )
                }
            }
        }
    }
}