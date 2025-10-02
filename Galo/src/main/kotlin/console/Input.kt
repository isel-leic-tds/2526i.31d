package console

data class LineCommand(val name: String, val args:List<String>)

tailrec fun readCommand(): LineCommand {
    print("> ")
    val line = readln().split(' ').filter { it.isNotBlank() }
    return if (line.isEmpty())
        readCommand()
    else LineCommand(line.first().uppercase(), line.drop(1))
}