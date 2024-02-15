package com.example.translationgame.ui.startgame

import androidx.lifecycle.ViewModel
import com.example.translationgame.data.local.GamePreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@HiltViewModel
class StartGameViewModel @Inject constructor(
    private val gamePreferences: GamePreferences
) : ViewModel() {

    private val _startGameViewState = MutableStateFlow<StartGameState>(StartGameState(highScore = null))
    val viewState: StateFlow<StartGameState> = _startGameViewState

    private val highScore: String? = gamePreferences.getScore()

    init {
        populateHighScore()
    }

    private fun populateHighScore() {
        _startGameViewState.value.highScore = highScore
    }
}