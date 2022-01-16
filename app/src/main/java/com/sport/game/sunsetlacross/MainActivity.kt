package com.sport.game.sunsetlacross

import android.content.Context
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.Crossfade
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sport.game.sunsetlacross.game.BallState
import com.sport.game.sunsetlacross.game.Game
import com.sport.game.sunsetlacross.game.GameState
import com.sport.game.sunsetlacross.ui.theme.SunsetLacrossTheme
import android.media.AudioManager

import android.media.SoundPool

open class MainActivity : ComponentActivity() {
    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContent {
            SunsetLacrossTheme {
                val viewModel = hiltViewModel<UiState>()
                val game by remember{ mutableStateOf(Game()) }
                game.restartGame()


                    GameUI(game,this)
                }
            }
        }
    }

@ExperimentalAnimationApi
@Composable
fun GameUI(game: Game , context : Context){


    val viewModel = hiltViewModel<UiState>()

    LaunchedEffect(Unit) {
        while (true) {
            withFrameNanos {
          game.update(it,context)
            }
        }
    }

    val interactionSource = remember { MutableInteractionSource() }

    // 未开始游戏前的上下动画

    val unStartedAnimation by animateFloatAsState(
        targetValue = when(game.birdState){
            BallState.Jumping -> 25f
            BallState.Falling -> (-25f)
        },
        tween(500)
    )

    if(game.gameState == GameState.Unstarted){
        if(unStartedAnimation == -25f) {
            game.birdState = BallState.Jumping
        } else if(unStartedAnimation == 25f)game.birdState = BallState.Falling
    }

    val birdPosition by animateFloatAsState(game.bird.y,
        tween(
            when(game.gameState){
                GameState.Running -> if(game.birdState == BallState.Falling) 150 else 50
                GameState.Over -> 1300
                GameState.Unstarted -> 0
            }, easing = LinearEasing
        )
    )

    Crossfade(targetState = viewModel.type) {
        if(it == 0){
            Background(R.drawable.bg)
        } else Background(R.drawable.bg)
    }

    // 柱子
    game.pipe.forEach{ pipe ->

        val pipeDownX by animateFloatAsState(pipe.pipeDownX, tween(150, easing = LinearEasing))
        val pipeUpX by animateFloatAsState(pipe.pipeUpX, tween(150, easing = LinearEasing))

        if(game.gameState == GameState.Running || game.gameState == GameState.Over){
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .offset(x = pipeDownX.dp),
                contentAlignment = Alignment.TopEnd
            ){
                Image(
                    painter = painterResource(id = R.drawable.uplacross),
                    contentDescription = null,
                    modifier = Modifier
                        .size(width = pipe.width, height = pipe.pipeDownHeight),
                    contentScale = ContentScale.FillBounds
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .offset(x = pipeUpX.dp),
                contentAlignment = Alignment.BottomEnd
            ){
                Image(
                    painter = painterResource(id = R.drawable.downlacross),
                    contentDescription = null,
                    modifier = Modifier
                        .size(width = pipe.width, height = pipe.pipeUpHeight),
                    contentScale = ContentScale.FillBounds
                )
            }
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickable(
                interactionSource = interactionSource,
                indication = null
            ) {
                if (game.gameState != GameState.Over) {
                    game.gameState = GameState.Running
                    game.birdState = BallState.Jumping
                }
            }
            .offset(
                y = when (game.gameState) {
                    GameState.Unstarted -> unStartedAnimation.dp
                    else -> birdPosition.dp
                }
            )
            .layout { measurable, constraints ->
                val placeable = measurable.measure(constraints)
                game.gameObject.screenHeight = placeable.measuredHeight.toDp()
                game.gameObject.screenWidth = placeable.measuredWidth.toDp()
                layout(placeable.width, placeable.height) {
                    placeable.placeRelative(0, 0)
                }
            },
        contentAlignment = Alignment.Center
    ){
        Image(
            painter = painterResource(id = R.drawable.yellowballpng),
            contentDescription = null,
            modifier = Modifier
                .size(width = game.bird.width, height = game.bird.height)
                .layout { measurable, constraints ->
                    val placeable = measurable.measure(constraints)
                    game.bird.height = placeable.measuredHeight.toDp()
                    game.bird.width = placeable.measuredWidth.toDp()
                    layout(placeable.width, placeable.height) {
                        placeable.placeRelative(0, 0)
                    }
                },
            contentScale = ContentScale.FillBounds
        )
    }

    Score(game)
    OverAlert(game)


}
