package com.example.translationgame.ui.startgame

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.translationgame.ui.navigation.ScreenNavigation.PlayGameScreen

@Composable
fun StartGameScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: StartGameViewModel = hiltViewModel()

) {
    val state = viewModel.viewState.collectAsState()

    Column(
        modifier = modifier
            .padding(32.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Translation game",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineMedium

        )
        state.value.highScore?.let {
            Text(
                modifier = modifier.padding(top = 6.dp),
                text = "Your highest score is: $it",
                style = MaterialTheme.typography.bodyLarge
            )
            // TODO if highScore is null, set other text
        }

        Button(
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 32.dp),
            onClick = {
                navController.navigate(PlayGameScreen.route)
            }) {
            Text(text = "START THE GAME")
        }
    }
}
