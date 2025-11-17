package pt.isel.tds.ttt.ui

import androidx.compose.runtime.*
import pt.isel.tds.storage.TextFileStorage
import pt.isel.tds.ttt.model.*

enum class EditMode { START, JOIN }

class AppViewModel {
    private val storage = TextFileStorage<Name,Game>("games", GameSerializer)
    var clash by mutableStateOf(Clash(storage))

    val game get() = (clash as? ClashRun)?.game

    fun startClash() { editMode = EditMode.START }

    fun newBoard() { clash = clash.new() }

    fun play(pos: Position) {
        if (game?.state is Run) clash = clash.play(pos)
    }

    // ------
    var viewScore by mutableStateOf(false)
        private set

    fun showScore() { viewScore = true }
    fun hideScore() { viewScore = false }

    // ------
    var editMode by mutableStateOf<EditMode?>(null)
}