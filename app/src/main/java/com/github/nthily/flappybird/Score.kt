package com.github.nthily.flappybird

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.github.nthily.flappybird.game.Game
import com.github.nthily.flappybird.game.GameState

@Composable
fun Score(game: Game) {
    if (game.gameState != GameState.Unstarted) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            contentAlignment = Alignment.TopEnd
        ) {
            Text(
                text = game.score.toString(),
                style = MaterialTheme.typography.h3,
                fontWeight = FontWeight.W700,
                color = Color.Yellow,
                fontFamily = FontFamily(
                    Font(R.font.century_gothic, FontWeight.W700)
                )
            )
        }
    }
}