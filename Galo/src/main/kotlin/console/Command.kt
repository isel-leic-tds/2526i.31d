package console

import model.*

abstract class Command(val syntaxArgs: String = "") {
    open fun execute(game: Game?, args:List<String>): Game? = game
    open val isTerminate: Boolean get() = false
}

object Play: Command("<position>") {
    override fun execute(game: Game?, args: List<String>): Game {
        val arg = requireNotNull(args.firstOrNull()) { "Missing position" }
        val pos = requireNotNull(arg.toPositionOrNull()) { "Invalid position $arg"}
        return checkNotNull(game) { "Game not started" }.play(pos)
    }
}

fun getCommands() = mapOf(
    "EXIT" to object: Command() {
        override val isTerminate get() = true
    },
    "NEW" to object: Command() {
        override fun execute(game: Game?, args: List<String>): Game = game?.new() ?: Game()
    },
    "PLAY" to Play
)