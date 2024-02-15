package com.example.translationgame.ui.playgame

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.translationgame.ui.playgame.components.FallingAnimationText
import com.example.translationgame.ui.playgame.components.Timer
import com.example.translationgame.ui.navigation.ScreenNavigation

@Composable
fun PlayGameScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: PlayGameViewModel = hiltViewModel()
) {
    val state = viewModel.playGameState.collectAsState()

    when (val screenState = state.value) {

        is PlayGameState.Loading -> {
            /*CircularProgressIndicator(
            )*/
        }

        is PlayGameState.GameOn -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(32.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(
                        text = "SCORE: ${screenState.currentScore}",
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Text(
                        modifier = modifier.padding(4.dp),
                        text = "LIVES: ${screenState.lives}",
                        style = MaterialTheme.typography.bodyLarge

                    )
                }
                Spacer(modifier = modifier.height(16.dp))
                Timer(resetTimer = screenState.resetTimer)
                Spacer(modifier = modifier.height(16.dp))
                Text(
                    style = MaterialTheme.typography.bodyLarge,
                    text = screenState.englishWord, modifier = modifier.padding(8.dp)
                )
                FallingAnimationText(text = screenState.spanishWord)
                Row(
                    modifier = modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Bottom
                ) {
                    Button(colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                        onClick = {
                            viewModel.handle(PlayGameIntent.OnIncorrectClicked)
                        }) {

                        Text("INCORRECT")
                    }
                    Button(colors = ButtonDefaults.buttonColors(containerColor = Color.Green),
                        onClick = {
                            viewModel.handle(PlayGameIntent.OnCorrectClicked)
                        }) {
                        Text("CORRECT")
                    }
                }
            }
        }

        is PlayGameState.Error -> {
            Box(modifier = modifier.fillMaxSize()) {
                Text(
                    text = "Error occurred",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.headlineLarge,
                )
            }
        }

        PlayGameState.GameOver -> {
            Column(
                modifier = Modifier
                    .padding(32.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Sorry, you lost :(",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.headlineLarge,
                )
                Button(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(top = 32.dp),
                    onClick = {
                        navController.navigate(ScreenNavigation.StartGameScreen.route)
                    }) {
                    Text(text = "PLAY AGAIN")
                }
            }
        }
    }
}
