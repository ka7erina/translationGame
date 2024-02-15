package com.example.translationgame.domain.repository

import com.example.translationgame.domain.model.WordsResult
import kotlinx.coroutines.flow.Flow

interface GameRepository {
    suspend fun getWords(): Flow<WordsResult>
}