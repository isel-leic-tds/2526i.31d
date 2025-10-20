package console

import model.*
import storage.TextFileStorage

fun main() {
    val storage = TextFileStorage<String,Game>("games", GameSerializer)
    var game = Game()
    val commands: Map<String, Command> = getCommands(storage)
    game.show()
    while (true) {
        val (name, args) = readCommand()
        val cmd = commands[name]
        if (cmd == null) println("Invalid Command $name")
        else try {
            game = cmd.execute(game, args)
            if (cmd.isTerminate) break
            game.show()
        } catch (ex: IllegalArgumentException) {
            println(ex.message)
            println("Use: $name ${cmd.syntaxArgs}")
        } catch (ex: IllegalStateException) {
            println(ex.message)
        }
    }
}
