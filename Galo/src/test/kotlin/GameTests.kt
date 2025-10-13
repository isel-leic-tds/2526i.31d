import kotlin.test.*
import model.*

class GameTest {
    @Test fun initialGameState() {
        val game = Game()
        assertEquals(Player.CROSS, game.first)
        assertTrue(game.board.isEmpty())
        assertTrue(game.state is Run)
        assertEquals(Player.CROSS, game.state.turn)
        assertEquals("{CROSS=0, BALL=0, null=0}", game.score.toString())
    }
    @Test fun playMoveUpdatesBoardAndTurn() {
        val pos = Position.values.random()
        val game = Game().play(pos)
        assertEquals(Player.CROSS, game.board[pos])
        assertTrue(game.state is Run)
        assertEquals(Player.BALL, game.state.turn)
    }
    @Test fun playMoveOnUsedPositionThrows() {
        val pos = Position.values.random()
        val game = Game().play(pos)
        assertFailsWith<IllegalStateException> { game.play(pos) }
    }
    // Helper function to play a list of moves
    private fun playMoves(vararg moves:Int): Game =
        moves.fold(Game()) { g, pos -> g.play(Position(pos)) }

    @Test fun winDetection() {
        // O X - | O X - | - X -
        val game = playMoves(1,0,4,3,7)  // X wins
        assertTrue(game.state is Win)
        assertEquals(Player.CROSS, game.state.winner)
        assertEquals(1, game.score[Player.CROSS])
    }
    @Test fun drawDetection() {
        // X O X | X O O | O X X
        val game = playMoves(0,1,2,4,3,5,7,6,8)
        assertTrue(game.state is Draw)
        assertEquals(1, game.score[null])
    }
    @Test fun newGameSwitchesFirstPlayer() {
        val game = playMoves(0,1,2)
        assertTrue(game.state is Run)
        assertFalse(game.board.isEmpty())
        val newGame = game.new()
        assertEquals(game.first.other, newGame.first)
        assertTrue(newGame.board.isEmpty())
        assertTrue(newGame.state is Run)
        assertEquals(0, game.score.values.sum())
        assertEquals(1, newGame.score[game.first])
    }
    @Test fun tryToPlayAfterWinThrows() {
        val game = playMoves(1,0,4,3,7) // X wins
        assertTrue(game.state is Win)
        assertFailsWith<IllegalStateException> {
            val emptyPos = (Position.values - game.board.keys).random()
            game.play(emptyPos) // Cannot play after win
        }
    }
}