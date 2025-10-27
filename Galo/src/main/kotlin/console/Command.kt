package console

import model.*

class Command(
    val syntaxArgs: String = "",
    val isTerminate: Boolean = false,
    val execute: (clash: Clash, args:List<String>) -> Clash = { c, _ -> c }
)

val Play = Command("<position>"){ clash, args ->
    val arg = requireNotNull(args.firstOrNull()) { "Missing position" }
    val pos = requireNotNull(arg.toPositionOrNull()) { "Invalid position $arg"}
    clash.play(pos)
}

fun storageCommand( fx: (Clash,Name)->Clash ) = Command("<name>") { clash, args ->
    val arg = requireNotNull(args.firstOrNull()) { "Missing name" }
    fx(clash,Name(arg))  // TODO: Clash.fx(Name)
}


fun getCommands() = mapOf(
    "EXIT" to Command(isTerminate = true),
    "NEW" to Command { clash, _ -> clash.new() },
    "PLAY" to Play,
    "SCORE" to Command { clash, _ -> clash.also { it.showScore() } },
    "START" to storageCommand { clash, name -> clash.start(name) },
    "JOIN" to storageCommand { clash, name -> clash.join(name) },
    // TODO: "REFRESH"
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
