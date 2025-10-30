package console

import model.*
import storage.TextFileStorage

fun main() {
    val storage = TextFileStorage<Name,Game>("games", GameSerializer)
    var clash = Clash(storage)
    val commands: Map<String, Command> = getCommands()
    while (true) {
        val (name, args) = readCommand()
        val cmd = commands[name]
        if (cmd == null) println("Invalid Command $name")
        else try {
            //clash = cmd.execute(clash, args)
            //clash = with(cmd) { clash.execute(args) }
            clash = clash.(cmd.execute)(args)
            if (cmd.isTerminate) break
            clash.show()
        } catch (ex: IllegalArgumentException) {
            println(ex.message)
            println("Use: $name ${cmd.syntaxArgs}")
        } catch (ex: IllegalStateException) {
            println(ex.message)
        }
    }
}
