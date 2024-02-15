package com.example.translationgame.ui.playgame

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.translationgame.data.local.GamePreferences
import com.example.translationgame.domain.model.Challenge
import com.example.translationgame.domain.model.Word
import com.example.translationgame.domain.model.WordsResult
import com.example.translationgame.domain.usecase.GetChallengeUseCase
import com.example.translationgame.domain.usecase.GetWordsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class PlayGameViewModel @Inject constructor(
    private val gamePreferences: GamePreferences,
    private val getWordsUseCase: GetWordsUseCase,
    private val getChallengeUseCase: GetChallengeUseCase
) : ViewModel() {

    private val _playGameState = MutableStateFlow<PlayGameState>(PlayGameState.Loading)
    val playGameState = _playGameState.asStateFlow()

    private lateinit var words: List<Word>
    private lateinit var challenge: Challenge
    private val highestScore = gamePreferences.getScore()?.toInt() ?: 0
    private var currentScore = 0
    private var lives = 3

    init {
        getWords()
    }

    private fun getWords() = viewModelScope.launch(Dispatchers.IO) {
        getWordsUseCase().collect { result ->
            when (result) {
                is WordsResult.Success -> {
                    words = result.words
                    challenge = getChallengeUseCase(words).also { challenge ->
                        _playGameState.value = PlayGameState.GameOn(
                            englishWord = challenge.englishWord,
                            spanishWord = challenge.spanishWord,
                            currentScore = currentScore.toString(),
                            highestScore = highestScore.toString(),
                            lives = lives.toString(),
                            resetTimer = false
                        )
                    }
                }

                is WordsResult.Error -> {
                    _playGameState.value = PlayGameState.Error
                }
            }
        }
    }

    private fun getNewChallenge() {
        challenge = getChallengeUseCase(words).also { challenge ->
            _playGameState.value = PlayGameState.GameOn(
                englishWord = challenge.englishWord,
                spanishWord = challenge.spanishWord,
                currentScore = currentScore.toString(),
                highestScore = highestScore.toString(),
                lives = lives.toString(),
                resetTimer = true
            )
        }
    }

    private fun updateScore() {
        currentScore++
        if (currentScore > highestScore) {
            gamePreferences.setScore(currentScore.toString())
        }
    }

    private fun decreaseLives() {
        lives--
    }

    fun handle(intent: PlayGameIntent) {
        when (intent) {
            is PlayGameIntent.OnCorrectClicked -> {
                if (lives > 1) {
                    if (challenge.isTranslationCorrect) {
                        updateScore()
                        getNewChallenge()
                    } else {
                        decreaseLives()
                        getNewChallenge()
                    }
                } else {
                    _playGameState.value = PlayGameState.GameOver
                }
            }


            is PlayGameIntent.OnIncorrectClicked -> {
                if (lives > 1) {
                    if (!challenge.isTranslationCorrect) {
                        println("XXXXX translation guessed")
                        updateScore()
                        getNewChallenge()
                    } else {
                        println("XXXXX translation not guessed")
                        decreaseLives()
                        getNewChallenge()
                    }
                } else {
                    _playGameState.value = PlayGameState.GameOver
                }
            }
        }
    }
}