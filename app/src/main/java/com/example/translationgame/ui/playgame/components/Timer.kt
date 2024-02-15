package com.example.translationgame.ui.playgame.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import kotlinx.coroutines.delay

const val STARTING_TIME_VALUE = 10
const val WAITING_TIME_MILLIS_VALUE = 1000L

@Composable
fun Timer(resetTimer: Boolean = false) {
    var timeLeft by remember { mutableStateOf(STARTING_TIME_VALUE) }
    val resetCounter = remember { mutableStateOf(0) }

    if (resetTimer) {
        resetCounter.value++
    }

    // Use resetTimer as a key for LaunchedEffect to restart the timer when resetTimer is true
    LaunchedEffect(key1 = resetCounter.value) {
        if (resetTimer) {
            timeLeft = STARTING_TIME_VALUE // Reset time left
        }

        // Launch a coroutine for the countdown
        while (timeLeft > 0) {
            delay(WAITING_TIME_MILLIS_VALUE)
            timeLeft--
        }
    }

    Text(text = "$timeLeft")
}