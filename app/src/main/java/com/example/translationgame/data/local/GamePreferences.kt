package com.example.translationgame.data.local

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

private const val SCORE_IDENTIFIER = "SCORE"

class GamePreferences @Inject constructor(@ApplicationContext context: Context) {

    private var prefs: SharedPreferences = context.getSharedPreferences(SCORE_IDENTIFIER, Context.MODE_PRIVATE)

    fun getScore(): String? = prefs.getString(SCORE_IDENTIFIER, null)

    fun setScore(score: String?) = prefs.edit().putString(SCORE_IDENTIFIER, score).apply()
}