package com.sport.game.sunsetlacross.game

import android.content.Context
import android.media.MediaPlayer
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import java.util.*
import kotlin.random.Random
import kotlin.random.nextInt
import com.sport.game.sunsetlacross.MainActivity

import com.sport.game.sunsetlacross.R
import com.sport.game.sunsetlacross.uianother.mediaPlayer
import kotlin.coroutines.CoroutineContext

enum class GameState {
    Unstarted, Running, Over
}

lateinit var music: MediaPlayer
var continueMusic = true

class Game {
    val gameObject = GameObject()
    val bird = Ball()
    val pipe by mutableStateOf<Queue<Pipe>>(LinkedList())

    var gameState by mutableStateOf(GameState.Unstarted)

    var birdState by mutableStateOf(BallState.Falling)

    var score by mutableStateOf(0)

    fun update(time: Long, context: Context) {

        if (gameState == GameState.Running) {
            when (birdState) {
                BallState.Jumping -> {
                    bird.jump(gameObject.jumpDistance)
                    birdState = BallState.Falling
                }
                BallState.Falling -> {
                    bird.y += 5f
                }
            }

            pipe.forEachIndexed { index, pipe ->
                pipe.pipeDownX -= 5f
                pipe.pipeUpX -= 5f

                // Up layer detection
                if (pipe.pipeDownHeight.value >= gameObject.screenHeight.value / 2 + bird.y.dp.value + 40 &&
                    (-pipe.pipeDownX.dp) + pipe.width >= gameObject.screenWidth / 2 - bird.width + 40.dp / 2 &&
                    (-pipe.pipeDownX.dp) <= gameObject.screenWidth / 2 + bird.width + 40.dp / 2
                ) {
                    music = MediaPlayer.create(context, R.raw.hit)
                    if (continueMusic)
                        music.start();
                    gameState = GameState.Over
                }

                // Down layer detection
                if (pipe.pipeUpHeight.value >= gameObject.screenHeight.value / 2 - bird.y.dp.value + 40 &&
                    (-pipe.pipeUpX.dp) + pipe.width >= gameObject.screenWidth / 2 - bird.width + 40.dp / 2 &&
                    (-pipe.pipeUpX.dp) <= gameObject.screenWidth / 2 + bird.width + 40.dp / 2
                ) {
                    music = MediaPlayer.create(context, R.raw.hit)
                    if (continueMusic)
                        music.start();

                    gameState = GameState.Over
                }

                // if the bird has reached the bottom
                if (bird.y.dp - bird.height / 2 >= gameObject.screenHeight / 2) {
                    gameState = GameState.Over
                }

                // if the bird has crossed a pipe
                if ((-pipe.pipeDownX.dp) >= gameObject.screenWidth / 2 + bird.width && !pipe.isCounted) {
                    pipe.isCounted = true
                    score += 5
                    gameObject.requestAdd = true
                }

            }
            if (gameObject.requestAdd) {
                addPipe()
                gameObject.requestAdd = false
            }
        }
        if (gameState == GameState.Over) {
            bird.y = gameObject.screenHeight.value / 2 - bird.height.value / 2
        }

    }

    fun restartGame() {
        gameState = GameState.Unstarted
        bird.y = 0f
        pipe.clear()
        score = 0
        addPipe()
    }

    private fun randomHeight(): Pair<Float, Float> {

        var totalHeight = 100

        if (gameObject.screenHeight != 0.dp) totalHeight =
            gameObject.screenHeight.value.toInt() - (bird.height.value * 4).toInt()

        val value = Random.nextInt(90..totalHeight)
            .toFloat()

        return Pair(value, totalHeight.toFloat() - value + 80)
    }


    private fun addPipe() {
        val height = randomHeight()
        pipe.add(Pipe(height.first.dp, height.second.dp))
    }


}

