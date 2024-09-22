package com.example.fluxsample.flux.fetchweather

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.example.fluxsample.flux.common.Action
import com.example.fluxsample.flux.common.Dispatcher
import com.example.fluxsample.flux.common.Store

class WeatherStore(
    dispatcher: Dispatcher,
) : Store(dispatcher) {
    private var _uiState: MutableState<WeatherUiState> = mutableStateOf(WeatherUiState.Initial)
    val uiState: State<WeatherUiState>
        get() = _uiState

    override fun onDispatch(payload: Action) {
        when (payload) {
            is FetchWeatherAction.Initial -> {
                _uiState.value = WeatherUiState.Initial
            }
            is FetchWeatherAction.Loading -> {
                _uiState.value = WeatherUiState.Loading
            }
            is FetchWeatherAction.Success -> {
                _uiState.value = WeatherUiState.Success(payload.payload)
            }
            is FetchWeatherAction.Failure -> {
                _uiState.value = WeatherUiState.Failure(payload.payload)
            }
        }
    }
}
