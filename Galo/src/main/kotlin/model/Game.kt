package model
import model.Player.*

const val BOARD_SIZE = 3
const val BOARD_CELLS = BOARD_SIZE * BOARD_SIZE

data class Game(
    val board: List<Player?> = List(BOARD_CELLS){ null },
    val first: Player = CROSS,
    val turn: Player = first,
)

fun Game.new() = Game(first = first.other)

fun Game.play(pos: Position): Game {
    //require(pos.index in board.indices) { "Invalid position $pos" }
    check(board[pos.index]==null) { "position not empty" }
    return copy(
        turn = turn.other,
        board = board.mapIndexed { idx, p -> if (idx==pos.index) turn else p }
    )
}

fun Game.isWinner(player: Player) =
   (0..6 step 3).any{ row -> (0..<3).all{ board[row+it]==player } }
|| (0..2).any { col -> (0..6 step 3).all{ board[col+it]==player }}
|| (0..8 step 4).all{ board[it]==player }
|| (2..6 step 2).all { board[it]==player }

fun Game.isDraw() = board.none { it==null }
