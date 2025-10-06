package model

@JvmInline
value class Position private constructor(val index: Int) {
    val row: Int get() = index / BOARD_SIZE
    val col: Int get() = index % BOARD_SIZE
    val backSlash: Boolean get() = row==col
    val slash: Boolean get() = row+col==BOARD_SIZE-1

    companion object {
       val values = List(BOARD_CELLS){ Position(it) }
       operator fun invoke(pos: Int): Position {
           require(pos in 0..<BOARD_CELLS)
           return values[pos]
       }
   }
}

fun String.toPositionOrNull(): Position? {
    val pos = toIntOrNull() ?: return null
    return if (pos !in 0..<BOARD_CELLS) null else Position.values[pos]
}

