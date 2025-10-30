package model

import storage.Storage

typealias GameStorage = Storage<Name, Game>

open class Clash(val storage: GameStorage) {
    private fun notStarted(): Clash = error("Clash not started")
    open fun play(pos: Position) = notStarted()
    open fun new() = notStarted()
    open fun refresh() = notStarted()
    fun start(name: Name) = ClashRun(
        storage, Player.CROSS, name,
        Game().also { storage.create(name,it) }
    )
    fun join(name: Name) = ClashRun(
        storage, Player.BALL, name,
        storage.read(name) ?: error("Game not found")
    )
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
    override fun refresh() =
        copy( (storage.read(name) as Game).also{
            check(it != game){ "No changes" }
        })
}

fun ClashRun.copy(game: Game = this.game) =
    ClashRun(storage,sidePlayer,name,game)

fun Clash.newAvailable() =
    (this is ClashRun) && (
            (game.state is Run) && (game.state.turn==sidePlayer)
        ||  (game.state !is Run) && (game.first==sidePlayer)
    )








