package com.example.fluxsample.flux.fetchweather

sealed class WeatherUiState {
    data object Initial : WeatherUiState()

    data object Loading : WeatherUiState()

    data class Success(
        val weatherDescription: String,
    ) : WeatherUiState()

    data class Failure(
        val errorMessage: String,
    ) : WeatherUiState()
}
