import console.show
import model.*
import kotlin.test.*

class GameSerializerTests {
    @Test fun serializeGameState() {
        val run = Run(Player.CROSS)
        val sr = StateSerializer.serialize(run)
        assertEquals("Run:CROSS",sr)
        assertEquals(run, StateSerializer.deserialize(sr))

        val win = Win(Player.BALL)
        val sw = StateSerializer.serialize(win)
        assertEquals("Win:BALL", sw)
        assertEquals(win, StateSerializer.deserialize(sw))

        val sd = StateSerializer.serialize(Draw)
        assertEquals("Draw:", sd)
        assertEquals(Draw, StateSerializer.deserialize(sd))
    }
    // Helper function to play a list of moves
    private fun playMoves(vararg moves:Int): Game =
        moves.fold(Game()) { g, pos -> g.play(Position(pos)) }

    @Test fun serializeRunGame() {
        val game = playMoves(4,0,1)
        //game.show()
        val s = GameSerializer.serialize(game)
        //println(s)
        assertEquals(game,GameSerializer.deserialize(s))
    }
    @Test fun serializeWinGame() {
        val game = playMoves(4,0,1,3,7)
        val s = GameSerializer.serialize(game)
        //println(s)
        assertEquals(game,GameSerializer.deserialize(s))
    }
    @Test fun serializeEmptyGame() {
        val game = Game()
        val s = GameSerializer.serialize(game)
        //println(s)
        assertEquals(game,GameSerializer.deserialize(s))
    }
}