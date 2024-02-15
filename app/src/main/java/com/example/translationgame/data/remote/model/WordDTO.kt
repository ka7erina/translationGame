package com.example.translationgame.data.remote.model

import com.google.gson.annotations.SerializedName

data class WordDTO(
    @SerializedName("text_eng") val englishWord: String?,
    @SerializedName("text_spa") val spanishWord: String?
)