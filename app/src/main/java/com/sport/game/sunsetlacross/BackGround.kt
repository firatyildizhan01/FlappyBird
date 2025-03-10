package com.sport.game.sunsetlacross

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource


@Composable
fun Background(
    img: Int
){
    Image(painterResource(id = img), contentDescription = null, modifier = Modifier.fillMaxSize(), contentScale = ContentScale.Crop)
}