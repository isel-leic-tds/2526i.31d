package model

import storage.Serializer

object GameSerializer : Serializer<Game> {
    override fun serialize(d: Game): String {
        TODO()
    }
    override fun deserialize(txt: String): Game {
        TODO("Not yet implemented")
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