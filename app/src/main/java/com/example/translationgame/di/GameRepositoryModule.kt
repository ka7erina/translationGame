package com.example.translationgame.di

import com.example.translationgame.domain.repository.GameRepository
import com.example.translationgame.data.repository.GameRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
interface GameRepositoryModule {

    @Binds
    @Singleton
    fun bindGameRepository(gameRepositoryImpl: GameRepositoryImpl): GameRepository
}
