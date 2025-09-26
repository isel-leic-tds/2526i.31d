import kotlin.test.*

class MutableStackTest {
    @Test fun `using stack`() {
        val stk = MutableStack<Char>()
        stk.push('A')
        stk.push('B')
        assertEquals('B',stk.top)
        stk.push('C')
        var result = ""
        while (!stk.isEmpty()) {
            val e = stk.pop()
            result += e
        }
        assertEquals("CBA",result)
    }
    @Test fun `empty stack conditions`() {
        val sut = MutableStack<Int>()
        assertTrue(sut.isEmpty())
        assertFailsWith<NoSuchElementException> { sut.top }
        assertFailsWith<NoSuchElementException> { sut.pop() }
    }
    @Test fun `not empty stack conditions`() {
        val sut = MutableStack<String>()
        sut.push("A")
        assertFalse(sut.isEmpty())
        assertEquals("A", sut.top)
        assertEquals("A", sut.pop())
        assertTrue(sut.isEmpty())
    }
    @Test fun `equality of stacks`() {
        // ...
    }
}