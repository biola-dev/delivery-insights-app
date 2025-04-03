package com.sendgrid.deliveryinsightsapp.domain.models

data class Weather(
    val temperature: Double,
    val condition: String,
    val windSpeed: Double?
)