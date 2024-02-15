package com.example.translationgame.domain.mapper

import com.example.translationgame.domain.model.Word
import com.example.translationgame.data.remote.model.WordDTO

object WordsMapper {
    fun List<WordDTO>.mapToWords(): List<Word> = this.map {
        Word(
            englishWord = it.englishWord.orEmpty(),
            spanishWord = it.spanishWord.orEmpty()
        )
    }
}