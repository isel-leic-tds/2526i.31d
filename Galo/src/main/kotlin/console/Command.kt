package console

import model.*
import storage.Storage

typealias GameStorage = Storage<String,Game>

class Command(
    val syntaxArgs: String = "",
    val isTerminate: Boolean = false,
    val execute: (game: Game, args:List<String>) -> Game = { g, _ -> g }
)

val Play = Command("<position>"){ game, args ->
    val arg = requireNotNull(args.firstOrNull()) { "Missing position" }
    val pos = requireNotNull(arg.toPositionOrNull()) { "Invalid position $arg"}
    game.play(pos)
}

fun namedCommand( fx: (Game,String)->Game ) = Command("<name>") { game, args ->
    val name = requireNotNull(args.firstOrNull()) { "Missing name" }
    fx(game,name)
}


fun getCommands(storage: GameStorage) = mapOf(
    "EXIT" to Command(isTerminate = true),
    "NEW" to Command { game, _ -> game.new() },
    "PLAY" to Play,
    "SCORE" to Command { game, _ -> game.also { it.score.show() } },
    "SAVE" to namedCommand { game, name ->
        storage.create(name,game)
        game
    },
    "LOAD" to namedCommand { _, name ->
        checkNotNull(storage.read(name)) { "$name not found" }
    }
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
