package model

@JvmInline
value class Position(val index: Int) {
    init {
        if (index !in 0..<BOARD_CELLS)
            throw IndexOutOfBoundsException("position invalid $index")
    }
}

fun String.toPositionOrNull(): Position? {
    val pos = toIntOrNull() ?: return null
    return if (pos !in 0..<BOARD_CELLS) null else Position(pos)
}

