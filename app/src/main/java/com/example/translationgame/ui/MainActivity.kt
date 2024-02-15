package com.example.translationgame.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.translationgame.ui.startgame.StartGameScreen
import com.example.translationgame.ui.navigation.ScreenNavigation
import com.example.translationgame.ui.playgame.PlayGameScreen
import com.example.translationgame.ui.theme.TranslationGameTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TranslationGameTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = ScreenNavigation.StartGameScreen.route,
                        enterTransition = {
                            EnterTransition.None
                        },
                        exitTransition = {
                            ExitTransition.None
                        },
                    ) {
                        composable(
                            route = ScreenNavigation.StartGameScreen.route
                        ) {
                            StartGameScreen(navController = navController)
                        }
                        composable(
                            route = ScreenNavigation.PlayGameScreen.route
                        ) {
                            PlayGameScreen(navController = navController)
                        }
                    }
                }
            }
        }
    }
}