package model
import model.Player.*

const val BOARD_SIZE = 2
const val BOARD_CELLS = BOARD_SIZE * BOARD_SIZE

typealias Board = Map<Position, Player>

data class Game(
    val board: Board = emptyMap(),
    val first: Player = CROSS,
    val state: GameState = GameRun(first),
)

sealed class GameState
class GameRun(val turn: Player): GameState()
class GameWin(val winner: Player): GameState()
class GameDraw: GameState()


fun Game.new() = Game(first = first.other)

fun Game.play(pos: Position): Game {
    check (state is GameRun) { "Game already finished" }
    check(pos !in board) { "position not empty" }
    val board = board + Pair(pos, state.turn)
    return copy(
        board = board,
        state = ...
    )
}

fun Game.isWinner(player: Player): Boolean {
    val positions = Position.values.filter { board[it]==player }
    if (positions.size<BOARD_SIZE) return false
    val counts = List(BOARD_SIZE){ row -> positions.count { it.row==row }} +
                 List(BOARD_SIZE){ col -> positions.count { it.col==col }} +
                 positions.count{ it.slash } + positions.count { it.backSlash }
    return counts.any{ it==BOARD_SIZE }
}

fun Game.isDraw() = board.size == BOARD_CELLS
