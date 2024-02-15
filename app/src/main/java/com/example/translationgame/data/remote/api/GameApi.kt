package com.example.translationgame.data.remote.api

import com.example.translationgame.data.remote.model.WordDTO
import com.example.translationgame.core.WORDS_ENDPOINT
import retrofit2.Response
import retrofit2.http.GET

interface GameApi {

    @GET(WORDS_ENDPOINT)
    suspend fun getWords(): Response<List<WordDTO>>
}