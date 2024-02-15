package com.example.translationgame.domain.model

data class Challenge(
    val englishWord: String,
    val spanishWord: String,
    val isTranslationCorrect: Boolean
)