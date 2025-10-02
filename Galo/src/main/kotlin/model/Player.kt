package model
import model.Player.*

enum class Player {
    CROSS, BALL
}

val Player.other get() = if (this== CROSS) BALL else CROSS