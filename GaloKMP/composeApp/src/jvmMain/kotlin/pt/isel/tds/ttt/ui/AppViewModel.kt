package pt.isel.tds.ttt.ui

import androidx.compose.runtime.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import pt.isel.tds.storage.MongoStorage
import pt.isel.tds.storage.TextFileStorage
import pt.isel.tds.storage.mongo.MongoDriver
import pt.isel.tds.ttt.model.*
import pt.isel.tds.ttt.model.Clash
import pt.isel.tds.ttt.model.GameRemovedException

enum class EditMode(val text: String) {
    START("Start"), JOIN("Join")
}

class AppViewModel(private val scope: CoroutineScope) {
    //private val storage = TextFileStorage<Name,_>("games", GameSerializer)
    private val driver = MongoDriver("galo")
    private val storage = MongoStorage<Name,_>("games", driver, GameSerializer)

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

    fun newBoard() {
        runOper { new() }
        waitingOther()
    }

    fun play(pos: Position) {
        if (game.state is Run && !isWaiting) {
            runOper { play(pos) }
            waitingOther()
        }
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
    fun doAction(name: Name) {
        runOper {
            when (checkNotNull(editMode)) {
                EditMode.START -> start(name)
                EditMode.JOIN -> join(name)
            }.also {
                editMode = null
                cancelWaiting()
            }
        }
        waitingOther()
    }
    fun end() {
        cancelWaiting()
        clash.deleteIfIsOwner()
        driver.close()
    }

    //----
    var message by mutableStateOf<String?>(null)
        private set
    fun clearMessage() { message = null }

    val isWaiting get() = job != null
    private var job by mutableStateOf<Job?>(null)

    private fun cancelWaiting() {
        job?.cancel()
        job = null
    }

    private fun waitingOther() {
        if (newIsAvailable) return
        job = scope.launch {
            do {
                delay(3000)
                try {
                    clash = clash.refresh(auto = true)
                } catch (ex: Exception) {
                    if (ex is IllegalArgumentException || ex is IllegalStateException)
                        message = ex.message
                    else throw ex
                    if (ex is GameRemovedException) {
                        clash = Clash(storage)
                        break
                    }
                }
            } while (!newIsAvailable)
            job = null
        }
    }
}








