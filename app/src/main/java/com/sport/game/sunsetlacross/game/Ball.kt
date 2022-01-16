package com.sport.game.sunsetlacross.game

import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp


enum class BallState {
    Jumping, Falling
}

class Ball {

    var x by mutableStateOf(0f)
    var y by mutableStateOf(0f)
    var width by mutableStateOf(40.dp)
    var height by mutableStateOf(40.dp)

    fun jump(distance: Float){
        y -= distance
    }

}