package model

const val EMPTY = ' '

data class Game(
    val board: List<Char> = listOf(
        EMPTY, EMPTY, EMPTY,
        EMPTY, EMPTY, EMPTY,
        EMPTY, EMPTY, EMPTY,
    ),
    val first: Char = 'X',
    val turn: Char = first,
)

fun Game.new() = Game(first = first.otherPlayer())

fun Game.canPlay(pos: Int) = board[pos]==EMPTY

fun Game.play(pos: Int) = copy(
    turn = turn.otherPlayer(),
    board = board.mapIndexed { idx, p -> if(idx==pos) turn else p }
)

fun Game.show() {
    board.chunked(3).forEachIndexed { idx, row ->
        println(row.joinToString("|") { " $it " })
        if (idx!=2) println("---+---+---")
    }
    println( when {
        isWinner('X') -> "winner: X"
        isWinner('O') -> "winner: O"
        isDraw() -> "draw"
        else -> "turn: $turn"
    } )
}

fun Game.isWinner(player: Char) =
   (0..6 step 3).any{ row -> (0..<3).all{ board[row+it]==player } }
|| false // check columns
|| false // check direct diagonal
|| false // check inverse diagonal

fun Game.isDraw() = board.none { it==EMPTY }


fun Char.otherPlayer() = if (this=='X') 'O' else 'X'