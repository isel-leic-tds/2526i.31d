package console

import model.*
import model.Player.*

fun Player?.toChar() = when(this) {
    CROSS -> 'X'
    BALL -> 'O'
    null -> ' '
}

private val separator = "---" + "+---".repeat(BOARD_SIZE-1)

fun Game.show() {
    board.chunked(BOARD_SIZE).forEachIndexed { idx, row ->
        println(row.joinToString("|") { " ${it.toChar()} " })
        if (idx!=BOARD_SIZE-1) println(separator)
    }
    println( when {
        isWinner(CROSS) -> "winner: ${CROSS.toChar()}"
        isWinner(BALL)  -> "winner: ${BALL.toChar()}"
        isDraw()        -> "draw"
        else            -> "turn: ${turn.toChar()}"
    } )
}

