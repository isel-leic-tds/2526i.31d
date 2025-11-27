package pt.isel.tds.ttt.model

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pt.isel.tds.storage.Storage

typealias GameStorage = Storage<Name, Game>

open class Clash(val storage: GameStorage) {
    private fun notStarted(): Clash = error("Clash not started")
    open fun play(pos: Position) = notStarted()
    open fun new() = notStarted()
    open suspend fun refresh(auto: Boolean = false) = notStarted()

    fun start(name: Name) = ClashRun(
        storage, Player.CROSS, name,
        Game().also { storage.create(name,it) }
    ).also { deleteIfIsOwner() }

    fun join(name: Name) = ClashRun(
        storage, Player.BALL, name,
        storage.read(name) ?: error("Game not found")
    ).also { deleteIfIsOwner() }

    open fun deleteIfIsOwner() { }
}

class ClashRun(
    storage: GameStorage,
    val sidePlayer: Player,
    val name: Name,
    val game: Game,
):Clash(storage) {
    override fun play(pos: Position) =
        copy(game.play(pos).also {
            val state = game.state as Run
            check(sidePlayer==state.turn) { "Not your turn" }
            storage.update(name,it)
        })
    override fun new(): ClashRun =
        if (newAvailable())
            copy(game.new().also { storage.update(name,it) })
        else error("New not available")
    override suspend fun refresh(auto: Boolean) =
        copy(
            storage.slowRead(name)?.also{
                check(auto || it != game){ "No changes" }
            }
            ?: throw GameRemovedException()
        )

    override fun deleteIfIsOwner() {
        if (sidePlayer == Player.CROSS) storage.delete(name)
    }
    fun newAvailable() = sidePlayer == when (val state = game.state) {
        is Run -> state.turn
        else -> game.first.other
    }
}

fun ClashRun.copy(game: Game = this.game) =
    ClashRun(storage,sidePlayer,name,game)


class GameRemovedException: IllegalStateException("Game removed")


suspend fun GameStorage.slowRead(name: Name): Game? {
    //println(Thread.currentThread().name)
    val g =  withContext(Dispatchers.IO) {
        //println(Thread.currentThread().name)
        Thread.sleep(5000)
        read(name)
    }
    //println(Thread.currentThread().name)
    return g
}


