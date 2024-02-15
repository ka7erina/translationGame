package com.example.translationgame.di

import com.example.translationgame.core.BASE_URL
import com.example.translationgame.data.remote.api.GameApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
}

@InstallIn(SingletonComponent::class)
@Module
object GameApiModule {

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): GameApi = retrofit.create(GameApi::class.java)
}