package com.example.pwassignment.di

import com.example.pwassignment.data.ApiService
import com.example.pwassignment.data.repository.CharacterRepository
import com.example.pwassignment.ui.strings.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {
    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        return Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(
            GsonConverterFactory.create()
        ).build().create(ApiService::class.java)
    }

    @Provides
    fun provideCharacterRepository(apiService: ApiService):CharacterRepository{
        return CharacterRepository(apiService)
    }


}