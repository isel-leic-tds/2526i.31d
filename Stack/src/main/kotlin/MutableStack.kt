class MutableStack<T> {
    private val stack: MutableList<T> = mutableListOf()

    fun push(e: T) {
        stack.addLast(e)
    }
    fun pop(): T = stack.last().also { stack.removeLast() }

    fun isEmpty() = stack.isEmpty()

    val top: T get() = stack.last()
}