package com.example.fluxsample.flux.fetchweather.data

import android.util.Log
import com.example.fluxsample.BuildConfig
import com.example.fluxsample.flux.fetchweather.model.CoordinateResponse
import com.example.fluxsample.flux.fetchweather.model.WeatherResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import javax.inject.Inject

const val API_KEY = BuildConfig.apiKey

class WeatherRemoteDataSource
    @Inject
    constructor() {
        private val client =
            HttpClient {
                install(ContentNegotiation) {
                    json(
                        Json {
                            ignoreUnknownKeys = true
                            isLenient = true
                        },
                    )
                }
            }

        suspend fun fetchCoordinate(cityName: String): List<CoordinateResponse>? {
            val url = "http://api.openweathermap.org/geo/1.0/direct?q=$cityName&limit=5&appid=$API_KEY"

            return try {
                val response: HttpResponse = client.get(url)
                response.body()
            } catch (e: Exception) {
                Log.e("WeatherRemoteDataSource", "Failed to fetch coordinate data", e)
                null
            }
        }

        suspend fun fetchWeather(
            lat: Double,
            lon: Double,
        ): WeatherResponse? {
            val url = "https://api.openweathermap.org/data/2.5/weather?lat=$lat&lon=$lon&lang=ja&appid=$API_KEY"

            return try {
                val response: HttpResponse = client.get(url)
                response.body()
            } catch (e: Exception) {
                Log.e("WeatherRemoteDataSource", "Failed to fetch weather data", e)
                null
            }
        }
    }
