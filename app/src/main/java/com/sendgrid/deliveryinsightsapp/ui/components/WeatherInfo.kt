package com.sendgrid.deliveryinsightsapp.ui.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sendgrid.deliveryinsightsapp.domain.models.Weather
import com.sendgrid.deliveryinsightsapp.util.formatWeather

@Composable
fun WeatherInfo(pickupWeather: Weather, dropOffWeather: Weather, recommendation: String) {
    Text("Pickup Weather: ${formatWeather(pickupWeather)}")
    Text("Drop-off Weather: ${formatWeather(dropOffWeather)}")
    Spacer(modifier = Modifier.height(8.dp))
    Text(
        "Recommendation: $recommendation",
        style = MaterialTheme.typography.bodyMedium
    )
}
