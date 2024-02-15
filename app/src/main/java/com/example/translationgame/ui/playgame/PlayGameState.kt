package com.example.translationgame.ui.playgame

sealed interface PlayGameState {
    data object Loading : PlayGameState

    data class GameOn(
        val englishWord: String = "",
        val spanishWord: String = "",
        val currentScore: String = "",
        val highestScore: String = "",
        val lives: String = "",
        val resetTimer: Boolean = false
    ) : PlayGameState

    data object GameOver : PlayGameState
    data object Error : PlayGameState
}