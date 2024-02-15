package com.example.translationgame.data.repository

import com.example.translationgame.data.remote.api.GameApi
import com.example.translationgame.domain.mapper.WordsMapper.mapToWords
import com.example.translationgame.domain.model.WordsResult
import com.example.translationgame.domain.repository.GameRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GameRepositoryImpl @Inject constructor(
    private val api: GameApi
) : GameRepository {
    override suspend fun getWords(): Flow<WordsResult> = flow {
        try {
            val response = api.getWords()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    emit(WordsResult.Success(body.mapToWords()))
                    println("XXXXX body $body")
                }
            }
        } catch (e: Exception) {
            // TODO handle different exceptions
            println("XXXXX error ${e.message}")
            emit(WordsResult.Error)
        }
    }
}