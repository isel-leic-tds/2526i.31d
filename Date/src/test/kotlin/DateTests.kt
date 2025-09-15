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
        val sut = Date(2020,0,15)
        assertEquals(0,sut.lastDayOfMonth)
    }
}