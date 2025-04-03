package com.sendgrid.deliveryinsightsapp.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sendgrid.deliveryinsightsapp.data.repository.WeatherRepository
import com.sendgrid.deliveryinsightsapp.domain.models.RouteHistory
import com.sendgrid.deliveryinsightsapp.domain.models.Weather
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class WeatherViewModel(private val repository: WeatherRepository) :
    ViewModel() {

    private val _pickup = MutableStateFlow("")
    val pickup: StateFlow<String> = _pickup

    private val _dropOff = MutableStateFlow("")
    val dropOff: StateFlow<String> = _dropOff

    private val _pickupWeather = MutableStateFlow<Weather?>(null)
    val pickupWeather: StateFlow<Weather?> = _pickupWeather

    private val _dropOffWeather = MutableStateFlow<Weather?>(null)
    val dropOffWeather: StateFlow<Weather?> = _dropOffWeather

    private val _recommendation = MutableStateFlow("")
    val recommendation: StateFlow<String> = _recommendation

    private val _history = MutableStateFlow<List<RouteHistory>>(emptyList())
    val history: StateFlow<List<RouteHistory>> = _history


    fun onPickupChanged(newPickup: String) {
        _pickup.value = newPickup
    }

    fun onDropOffChanged(newDropOff: String) {
        _dropOff.value = newDropOff
    }

    fun fetchWeatherData() {
        if (_pickup.value.isBlank() || _dropOff.value.isBlank()) {
            return
        }
        viewModelScope.launch {

            val pickupWeatherData =
                repository.fetchWeather(location = pickup.value)
            val dropOffWeatherData = repository.fetchWeather(dropOff.value)

            _pickupWeather.value = pickupWeatherData
            _dropOffWeather.value = dropOffWeatherData

            _recommendation.value =
                getDeliveryRecommendation(pickupWeatherData)

            repository.addRouteHistory(
                RouteHistory(
                    pickup = pickup.value,
                    dropOff = dropOff.value
                )
            )
            _history.value = repository.getHistory()

            _pickup.value = ""
            _dropOff.value = ""

        }
    }

    private fun getDeliveryRecommendation(weather: Weather): String {
        return when {
            weather.condition.contains("Clear", ignoreCase = true) ||
                    weather.condition.contains(
                        "Light Rain",
                        ignoreCase = true
                    ) -> "Safe for delivery"

            weather.condition.contains("Heavy Rain", ignoreCase = true) ||
                    weather.condition.contains(
                        "Thunderstorm",
                        ignoreCase = true
                    ) -> "Delay advised"

            else -> "Check conditions"
        }
    }

}