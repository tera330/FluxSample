package com.example.fluxsample.flux

import androidx.lifecycle.ViewModel
import com.example.fluxsample.flux.common.Dispatcher
import com.example.fluxsample.flux.fetchweather.WeatherStore

class MainViewModel(
    dispatcher: Dispatcher,
) : ViewModel() {
    val weatherStore = WeatherStore(dispatcher)
}
