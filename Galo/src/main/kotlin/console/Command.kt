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
    "EXIT" to Command(isTerminate = true) { deleteIfIsOwner(); this },
    "NEW" to Command { new() },
    "PLAY" to Play,
    "SCORE" to Command { showScore(); this },
    "START" to storageCommand { name -> start(name) },
    "JOIN" to storageCommand { name -> join(name) },
    "REFRESH" to Command { refresh() }
)