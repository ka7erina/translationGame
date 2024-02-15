package com.example.translationgame.util

import kotlin.random.Random

fun getRandomBoolean(): Boolean = Random.nextBoolean()

fun getRandomInt(end: Int): Int = Random.nextInt(0, end)