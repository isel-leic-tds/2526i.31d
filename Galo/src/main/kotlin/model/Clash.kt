package model

import storage.Storage

typealias GameStorage = Storage<Name, Game>

open class Clash(val storage: GameStorage) {
    open fun play(pos: Position): Clash { error("Clash not started") }
    open fun new(): Clash { error("Clash not started") }
    fun start(name: Name) = ClashRun(
        storage, Player.CROSS, name,
        Game().also { storage.create(name,it) }
    )
    fun join(name: Name): Clash = TODO()
}

class ClashRun(
    storage: GameStorage,
    val sidePlayer: Player,
    val name: Name,
    val game: Game,
):Clash(storage) {
    override fun play(pos: Position) =
        ClashRun(storage, sidePlayer, name, game.play(pos))
    override fun new() =
        ClashRun(storage, sidePlayer, name, game.new())
}

