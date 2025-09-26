class MutableStack1<T> {
    private var stack: List<T> = emptyList()

    fun push(e: T) {
        stack = stack + e
    }
    fun pop(): T = stack.last().also { stack = stack.dropLast(1) }

    fun isEmpty() = stack.isEmpty()

    val top: T get() = stack.last()
}