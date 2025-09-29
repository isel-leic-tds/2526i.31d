import model.*

fun main() {
    var game: Game? = null
    while (true) {
        print("> ")
        val cmd = readln().uppercase().split(' ')
        when(val name = cmd[0]) {
            "EXIT" -> break
            "NEW" -> game = game?.new() ?: Game()
            "PLAY" -> {
                val pos = cmd[1].toInt()
                if (game!=null && game.canPlay(pos))
                    game = game.play(pos)
            }
            else -> println("Invalid Command $name")
        }
        game?.show()
    }
}