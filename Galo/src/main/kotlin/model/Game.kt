package model
import model.Player.*

const val BOARD_SIZE = 3
const val BOARD_CELLS = BOARD_SIZE * BOARD_SIZE

typealias Board = Map<Position, Player>
typealias Score = Map<Player?,Int>

fun Score() = (Player.entries + null).associateWith { 0 }

fun Score.advance(p: Player?) = plus(p to getValue(p)+1)

data class Game(
    val board: Board = emptyMap(),
    val first: Player = CROSS,
    val state: GameState = Run(first),
    val score: Score = Score()
)

sealed class GameState
class Run(val turn: Player): GameState()
class Win(val winner: Player): GameState()
object Draw: GameState()


fun Game.new() = Game(
    first = first.other,
    score = if (state is Run) score.advance(state.turn.other)
            else score
)

fun Game.play(pos: Position): Game {
    check (state is Run) { "Game already finished" }
    check(pos !in board) { "position not empty" }
    val newBoard = board + Pair(pos, state.turn)
    val newState = when {
        newBoard.isWinnerIn(pos) -> Win(state.turn)
        newBoard.size == BOARD_CELLS -> Draw
        else -> Run(state.turn.other)
    }
    return copy(
        board = newBoard,
        state = newState,
        score = when (newState) {
            is Win -> score.advance(newState.winner)
            is Draw -> score.advance(null)
            is Run -> score
        }
    )
}

fun Board.isWinnerIn(p: Position): Boolean {
    val player = getValue(p)
    val places = filterValues { it==player }.keys
    return places.count{ it.col==p.col } == BOARD_SIZE ||
            places.count{ it.row==p.row } == BOARD_SIZE ||
            p.backSlash && places.count{ it.backSlash } == BOARD_SIZE ||
            p.slash && places.count{ it.slash } == BOARD_SIZE
}

