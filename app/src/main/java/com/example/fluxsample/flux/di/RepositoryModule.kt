package com.example.fluxsample.flux.di

import com.example.fluxsample.flux.fetchweather.FetchWeatherRepositoryImpl
import com.example.fluxsample.flux.fetchweather.data.FetchWeatherRepository
import com.example.fluxsample.flux.fetchweather.data.WeatherRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideWeatherRepository(remoteDataSource: WeatherRemoteDataSource): FetchWeatherRepository =
        FetchWeatherRepositoryImpl(remoteDataSource)
}
