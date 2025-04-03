package com.sendgrid.deliveryinsightsapp.util

import com.sendgrid.deliveryinsightsapp.domain.models.Weather

fun formatWeather(weather: Weather): String {
    val wind = weather.windSpeed?.let { ", Wind: $it km/h" } ?: ""
    return "Temp: ${weather.temperature}°C, Condition: ${weather.condition}$wind"
}
