package com.example.translationgame.domain.usecase

import com.example.translationgame.domain.repository.GameRepository
import javax.inject.Inject

class GetWordsUseCase @Inject constructor(
    private val gameRepository: GameRepository
) {
    suspend operator fun invoke() = gameRepository.getWords()
}