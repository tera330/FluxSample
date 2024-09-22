package com.example.fluxsample.flux.fetchweather

import com.example.fluxsample.flux.common.Action
import com.example.fluxsample.flux.common.Dispatcher
import com.example.fluxsample.flux.fetchweather.data.FetchWeatherRepository
import javax.inject.Inject

class FetchWeatherActionCreator
    @Inject
    constructor(
        private val dispatcher: Dispatcher,
        private val repository: FetchWeatherRepository,
    ) {
        suspend fun fetchWeatherByCityName(cityName: String) {
            dispatcher.dispatch(FetchWeatherAction.Loading)
            try {
                val response =
                    repository
                        .fetchWeatherByCityName(cityName)
                        ?.weather
                        ?.get(0)
                        ?.description
                val weather = response.toString()
                dispatcher.dispatch(FetchWeatherAction.Success(weather))
            } catch (e: Exception) {
                dispatcher.dispatch(FetchWeatherAction.Failure("Failed to fetch weather."))
            }
        }
    }

sealed class FetchWeatherAction : Action {
    data object Initial : FetchWeatherAction() {
        override val payload: Any? = null
    }

    data object Loading : FetchWeatherAction() {
        override val payload: Any? = null
    }

    data class Success(
        override val payload: String,
    ) : FetchWeatherAction()

    data class Failure(
        override val payload: String,
    ) : FetchWeatherAction()
}
