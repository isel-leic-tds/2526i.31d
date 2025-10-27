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
    Position.values.map { board[it] }.chunked(BOARD_SIZE).forEachIndexed { idx, row ->
        println(row.joinToString("|") { " ${it.toChar()} " })
        if (idx!=BOARD_SIZE-1) println(separator)
    }
    println( when(state) {
        is Win  -> "winner: ${state.winner.toChar()}"
        is Draw -> "draw"
        is Run  -> "turn: ${state.turn.toChar()}"
    } )
}

fun Score.show() {
    entries
        .map { (key,value) ->
            val name = key?.let{ "Player ${it.toChar()}" } ?: "Draw"
            "$name = $value "
        }
        .forEach(::println)
}

fun Clash.show() { // = (this as? ClashRun)?.game?.show()
    if (this !is ClashRun) return
    game.show()
}

fun Clash.showScore() {
    check(this is ClashRun){ "Clash not started" }
    game.score.show()
}
