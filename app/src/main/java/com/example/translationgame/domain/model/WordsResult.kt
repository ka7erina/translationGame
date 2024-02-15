package com.example.translationgame.domain.model

interface WordsResult {

    data class Success(val words: List<Word>) : WordsResult
    object Error: WordsResult
    object Loading: WordsResult
}