package pt.isel.tds.ttt.model

import pt.isel.tds.storage.*

object GameSerializer : Serializer<Game> {
    override fun serialize(d: Game): String = buildString {
        appendLine(d.first)
        appendLine(StateSerializer.serialize(d.state))
        appendLine(d.board.entries.joinToString(" "){ (k,v) -> "${k.index}:$v" })
        appendLine(d.score.entries.joinToString(" "){ (k,v) -> "$k=$v"})
    }
    override fun deserialize(txt: String) = txt
        .split('\n')
        .let { (l1,l2,l3,l4) ->
            Game(
                first = Player.valueOf(l1),
                state = StateSerializer.deserialize(l2),
                board = if (l3.isEmpty()) emptyMap()
                    else l3.split(' ').associate {
                    val (index, player) = it.split(':')
                    Position(index.toInt()) to Player.valueOf(player)
                },
                score = l4.split(' ').associate {
                    val (playerTxt,points) = it.split('=')
                    val player = if (playerTxt=="null") null else Player.valueOf(playerTxt)
                    player to points.toInt()
                }
            )
        }
}


// val gs = Run(Player.CROSS)
// StateSerializer.serialize(gs) == "Run:CROSS"
object StateSerializer : Serializer<GameState> {
    override fun serialize(d: GameState) = when(d) {
        is Run -> "Run:${d.turn}"
        is Win -> "Win:${d.winner}"
        is Draw -> "Draw:"
    }
    override fun deserialize(txt: String): GameState {
        val (type,player) = txt.split(":")
        return when(type) {
            "Run" -> Run(Player.valueOf(player))
            "Win" -> Win(Player.valueOf(player))
            "Draw" -> Draw
            else -> error("Invalid state $type")
        }
    }

}