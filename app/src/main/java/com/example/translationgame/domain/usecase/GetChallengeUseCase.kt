package com.example.translationgame.domain.usecase

import com.example.translationgame.domain.model.Word
import com.example.translationgame.domain.model.Challenge
import com.example.translationgame.util.getRandomBoolean
import com.example.translationgame.util.getRandomInt
import javax.inject.Inject

class GetChallengeUseCase @Inject constructor() {
    operator fun invoke(words: List<Word>): Challenge {
        //  pick a random index to get an english word
        val englishWordIndex = getRandomInt(words.size - 1)
        val spanishWordIndex = if (getRandomBoolean()) englishWordIndex else getRandomInt(words.size - 1)

        return Challenge(
            englishWord = words[englishWordIndex].englishWord,
            spanishWord = words[spanishWordIndex].spanishWord,
            isTranslationCorrect = spanishWordIndex == englishWordIndex
        )
    }
}