package com.example.fluxsample.flux.fetchweather.data

import com.example.fluxsample.flux.fetchweather.model.WeatherResponse

interface FetchWeatherRepository {
    suspend fun fetchWeatherByCityName(cityName: String): WeatherResponse?
}
