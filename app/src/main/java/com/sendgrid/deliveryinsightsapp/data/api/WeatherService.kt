package com.sendgrid.deliveryinsightsapp.data.api

import com.sendgrid.deliveryinsightsapp.domain.models.Weather
import kotlinx.serialization.Serializable
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    @GET("weather")
    suspend fun getWeather(
        @Query("q") location: String,
        @Query("appid") apiKey: String,
        @Query("units") units: String = "metric"
    ): WeatherResponse
}

@Serializable
data class WeatherResponse(
    val main: Main,
    val weather: List<WeatherDescription>,
    val wind: Wind
) {
    fun toWeather(): Weather {
        return Weather(
            temperature = main.temp,
            condition = weather.firstOrNull()?.description ?: "Unknown",
            windSpeed = wind.speed
        )
    }
}

@Serializable
data class Main(
    val temp: Double
)

@Serializable
data class WeatherDescription(
    val description: String
)

@Serializable
data class Wind(
    val speed: Double
)