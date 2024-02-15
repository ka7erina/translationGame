package com.example.translationgame.ui.navigation

sealed class ScreenNavigation(val route: String) {
    data object StartGameScreen : ScreenNavigation("start_game_screen")
    data object PlayGameScreen : ScreenNavigation("play_game_screen")
}