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
}