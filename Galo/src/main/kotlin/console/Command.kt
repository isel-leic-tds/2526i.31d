package console

import model.*

class Command(
    val syntaxArgs: String = "",
    val isTerminate: Boolean = false,
    val execute: Clash.(args:List<String>) -> Clash = { this }
)

val Play = Command("<position>"){ args ->
    val arg = requireNotNull(args.firstOrNull()) { "Missing position" }
    val pos = requireNotNull(arg.toPositionOrNull()) { "Invalid position $arg"}
    play(pos)
}

fun storageCommand( fx: Clash.(Name)->Clash ) = Command("<name>") { args ->
    val arg = requireNotNull(args.firstOrNull()) { "Missing name" }
    fx(Name(arg))
}


fun getCommands() = mapOf(
    "EXIT" to Command(isTerminate = true),
    "NEW" to Command { new() },
    "PLAY" to Play,
    "SCORE" to Command { showScore(); this },
    "START" to storageCommand { name -> start(name) },
    "JOIN" to storageCommand { name -> join(name) },
    "REFRESH" to Command { refresh() }
)

/* POO style
abstract class Command(val syntaxArgs: String = "") {
    open fun execute(game: Game, args:List<String>): Game = game
    open val isTerminate: Boolean get() = false
}

object Play: Command("<position>") {
    override fun execute(game: Game, args: List<String>): Game {
        val arg = requireNotNull(args.firstOrNull()) { "Missing position" }
        val pos = requireNotNull(arg.toPositionOrNull()) { "Invalid position $arg"}
        return game.play(pos)
    }
}

fun getCommands() = mapOf(
    "EXIT" to object: Command() {
        override val isTerminate get() = true
    },
    "NEW" to object: Command() {
        override fun execute(game: Game, args: List<String>) = game.new()
    },
    "PLAY" to Play,
    "SCORE" to object: Command() {
        override fun execute(game: Game, args: List<String>) =
            game.also { it.score.show() }
    }
)*/
