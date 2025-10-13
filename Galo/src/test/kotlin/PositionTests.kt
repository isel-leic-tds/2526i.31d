import kotlin.test.*
import model.*

class PositionTests {
    @Test fun topLeftPosition() {
        val sut = Position(0)
        assertEquals(0, sut.index)
        assertEquals(0, sut.row)
        assertEquals(0, sut.col)
        assertTrue(sut.backSlash)
        assertFalse(sut.slash)
    }
    @Test fun topRightPosition() {
        val sut = Position(BOARD_SIZE-1)
        assertEquals(0, sut.row)
        assertEquals(sut.index, sut.col)
        assertFalse(sut.backSlash)
        assertTrue(sut.slash)
    }
    @Test fun middlePosition() {
        @Suppress("KotlinConstantConditions")
        if (BOARD_SIZE % 2 == 0) return // Skip if not 3x3, 5x5, etc.
        val sut = Position(BOARD_CELLS/2)
        assertEquals(BOARD_SIZE/2, sut.row)
        assertEquals(BOARD_SIZE/2, sut.col)
        assertTrue(sut.backSlash)
        assertTrue(sut.slash)
    }
    @Test fun equalPositions() {
        val sut1 = (2*BOARD_SIZE+1).toString().toPositionOrNull()
        val sut2 = Position(2*BOARD_SIZE+1)
        assertEquals(sut1, sut2)
        assertFalse(sut1 != sut2)
    }
    @Test fun invalidPositions() {
        assertFailsWith<IndexOutOfBoundsException> { Position(-1) }
        assertFailsWith<IndexOutOfBoundsException> { Position(BOARD_CELLS) }
        assertNull( "abc".toPositionOrNull() )
        assertNull("-3".toPositionOrNull())
    }
}

