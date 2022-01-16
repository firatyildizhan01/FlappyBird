package com.sport.game.sunsetlacross

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UiState @Inject constructor()
    : ViewModel() {

        var type by mutableStateOf(0)
}