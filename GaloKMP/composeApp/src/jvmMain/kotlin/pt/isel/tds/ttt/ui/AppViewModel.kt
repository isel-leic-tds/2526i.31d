package pt.isel.tds.ttt.ui

import androidx.compose.runtime.*
import pt.isel.tds.storage.TextFileStorage
import pt.isel.tds.ttt.model.*

enum class EditMode(val text: String) {
    START("Start"), JOIN("Join")
}

class AppViewModel {
    private val storage = TextFileStorage<Name,Game>("games", GameSerializer)
    var clash by mutableStateOf(Clash(storage))

    val isRun get() = clash is ClashRun
    val newIsAvailable get() = (clash as? ClashRun)?.newAvailable() ?: false
    val you get() = (clash as ClashRun).sidePlayer
    val name get() = (clash as ClashRun).name
    val game get() = (clash as ClashRun).game

    private fun runOper( oper: Clash.() -> Clash ) {
        try {
            clash = clash.oper()
        } catch (ex: Exception) {
            if (ex is IllegalArgumentException || ex is IllegalStateException)
                message = ex.message
            else throw ex
        }
    }

    fun refresh() = runOper { refresh() }

    fun newBoard() = runOper { new() }

    fun play(pos: Position) {
        if (game.state is Run) runOper { play(pos) }
    }

    // ------
    var viewScore by mutableStateOf(false)
        private set
    fun showScore() { viewScore = true }
    fun hideScore() { viewScore = false }

    // ------
    var editMode by mutableStateOf<EditMode?>(null)
        private set
    fun startClash() { editMode = EditMode.START }
    fun joinClash() { editMode = EditMode.JOIN }
    fun cancelEdit() { editMode = null }
    fun doAction(name: Name) = runOper {
        when(checkNotNull(editMode)) {
            EditMode.START -> start(name)
            EditMode.JOIN  -> join(name)
        }.also { editMode = null }
    }
    fun end() { clash.deleteIfIsOwner() }

    //----
    var message by mutableStateOf<String?>(null)
        private set
    fun clearMessage() { message = null }
}