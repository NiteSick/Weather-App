package com.example.weatherapp.di

import android.app.Application
import com.example.weatherapp.Network.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.internal.managers.ApplicationComponentManager
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponentManager::class)
class AppModule {
    @Provides
    fun providesBaseUrl():String = "https://api.openweathermap.org/data/2.5/"

    @Provides
    @Singleton
    fun provideRetrofitBuilder(baseUrl: String) :Retrofit = Retrofit.Builder().baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create()).build()

    @Provides
    fun provideApiService(retrofit: Retrofit):ApiService =
        retrofit.create(ApiService::class.java)

}