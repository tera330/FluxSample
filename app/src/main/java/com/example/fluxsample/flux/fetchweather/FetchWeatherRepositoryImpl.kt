package com.example.fluxsample.flux.fetchweather

import com.example.fluxsample.flux.fetchweather.data.FetchWeatherRepository
import com.example.fluxsample.flux.fetchweather.data.WeatherRemoteDataSource
import com.example.fluxsample.flux.fetchweather.model.WeatherResponse
import javax.inject.Inject

class FetchWeatherRepositoryImpl
    @Inject
    constructor(
        private val remoteDataSource: WeatherRemoteDataSource,
    ) : FetchWeatherRepository {
        override suspend fun fetchWeatherByCityName(cityName: String): WeatherResponse? {
            val location = remoteDataSource.fetchCoordinate(cityName)
            return if (!location.isNullOrEmpty()) {
                remoteDataSource.fetchWeather(location[0].lat, location[0].lon)
            } else {
                null
            }
        }
    }
