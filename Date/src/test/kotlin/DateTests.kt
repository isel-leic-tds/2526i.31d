import kotlin.test.*

class DateTests {

    @Test
    fun testDateCreation() {
        val date = Date(2025, 9, 25)
        assertEquals(2025, date.year)
        assertEquals(9, date.month)
        assertEquals(25, date.day)
    }
    @Test
    fun defaultDay1() {
        val sut = Date(2025, 9)
        assertEquals(1, sut.day)
    }
    @Test
    fun defaultMonthAndDay1() {
        val sut = Date(2025)
        assertEquals(1, sut.day)
        assertEquals(1,sut.month)
    }
    @Test
    fun defaultDate() {
        val sut = Date()
        assertEquals(1, sut.day)
        assertEquals(1,sut.month)
        assertEquals(2000,sut.year)
    }
    @Test fun isLeapYear() {
        val sut = Date(2020)
        assertTrue(sut.leapYear)
    }
    @Test fun isNotLeapYear() {
        val sut = Date(2025)
        assertFalse(sut.leapYear)
    }
    @Test fun lastDayOfMonth() {
        val sut = Date(2025,9,15)
        assertEquals(30,sut.lastDayOfMonth)
    }
    @Test fun lastDayOfMonthLeapYear() {
        val ex = assertFailsWith<IllegalArgumentException>{
            Date(2020,0,15)
        }
        assertEquals("Invalid month 0",ex.message)
    }
    @Test fun `Add days to Date in same month`() {
        val sut = Date(2025,9,22) + 5
        assertEquals(27,sut.day)
        assertEquals(9,sut.month)
        assertEquals(2025,sut.year)
    }
    @Test fun `Add days to Date in same year`() {
        val sut = 9 + Date(2025,9,22)
        assertEquals(1,sut.day)
        assertEquals(10,sut.month)
        assertEquals(2025,sut.year)
    }
    @Test fun `Add days to Date in other year`() {
        val sut = 1 + Date(2025,12,31)
        assertEquals(1,sut.day)
        assertEquals(1,sut.month)
        assertEquals(2026,sut.year)
    }
    @Test fun `compare two dates`() {
        val sut = Date(2025,9,22)
        assertEquals(sut,sut) // sut == sut
        val other = Date(2025,9,22)
        assertEquals(other,sut)
        assertTrue(other == sut)
        // a == b  -> a.equals(b)
    }
}