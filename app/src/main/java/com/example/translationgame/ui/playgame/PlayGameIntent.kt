package com.example.translationgame.ui.playgame

sealed interface PlayGameIntent {

    data object OnCorrectClicked : PlayGameIntent
    data object OnIncorrectClicked : PlayGameIntent
}