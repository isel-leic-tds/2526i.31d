fun main() {
    val stk = MutableStack<Char>()
    stk.push('A')
    stk.push('B')
    print(stk.top)
    stk.push('C')
    while (!stk.isEmpty()) {
        val e = stk.pop()
        print(e)
    }
    //stk.pop()
    // Output -> BCBA
}