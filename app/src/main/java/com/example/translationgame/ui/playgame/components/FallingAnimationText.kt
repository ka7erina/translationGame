package com.example.translationgame.ui.playgame.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextMotion
import androidx.compose.ui.unit.dp

@Composable
fun FallingAnimationText(text: String) {
    var enabled by remember { mutableStateOf(false) }

    val transition = updateTransition(enabled, label = "fallingTransition")
    val dpTransition by transition.animateDp(transitionSpec = {
        tween(durationMillis = 10000, easing = LinearEasing)
    }, label = "fallingTransition") { state ->
        if (state) 550.dp else 0.dp
    }

    LaunchedEffect(key1 = enabled) {
        enabled = true
    }
    Text(
        modifier = Modifier
            .offset(y = dpTransition),
        text = text,
        style = LocalTextStyle.current.copy(textMotion = TextMotion.Animated)
    )
}