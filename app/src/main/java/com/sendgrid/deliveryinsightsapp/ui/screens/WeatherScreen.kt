package com.sendgrid.deliveryinsightsapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.sendgrid.deliveryinsightsapp.domain.models.RouteHistory
import com.sendgrid.deliveryinsightsapp.domain.models.Weather
import com.sendgrid.deliveryinsightsapp.ui.viewmodels.WeatherViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherScreen(viewModel: WeatherViewModel) {
    val pickup by viewModel.pickup.collectAsState()
    val dropOff by viewModel.dropOff.collectAsState()
    val pickupWeather by viewModel.pickupWeather.collectAsState()
    val dropOffWeather by viewModel.dropOffWeather.collectAsState()
    val recommendation by viewModel.recommendation.collectAsState()
    val history by viewModel.history.collectAsState()

    Scaffold(topBar = {
        TopAppBar(title = { Text("Weather Delivery App") })
    }) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            OutlinedTextField(
                value = pickup,
                onValueChange = {
                    viewModel.onPickupChanged(it)
                },
                label = { Text("Pickup Location") },
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFFE87000),
                    unfocusedBorderColor = Color.Gray
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = dropOff,
                onValueChange = { viewModel.onDropOffChanged(it) },
                label = { Text("Drop-off Location") },
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFFE87000),  // Orange when active
                    unfocusedBorderColor = Color.Gray // Gray when inactive
                )
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    viewModel.fetchWeatherData()
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(Color(0xFFE87000))

            ) {
                Text("Get Weather")
            }
            Spacer(modifier = Modifier.height(16.dp))
            if (pickupWeather != null && dropOffWeather != null) {
                Text("Pickup Weather: ${formatWeather(pickupWeather!!)}")
                Text("Drop-off Weather: ${formatWeather(dropOffWeather!!)}")
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    "Recommendation: $recommendation", style = MaterialTheme
                        .typography.bodyMedium
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text("Recent Routes", style = MaterialTheme.typography.titleMedium)
            LazyColumn {
                items(history) { route ->
                    HistoryItem(route)
                }
            }
        }
    }
}

@Composable
fun HistoryItem(route: RouteHistory) {
    Card(
        modifier = Modifier
            .padding(vertical = 4.dp)
            .fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text("Pickup: ${route.pickup}")
            Text("Drop-off: ${route.dropOff}")
        }
    }
}

private fun formatWeather(weather: Weather): String {
    val wind = weather.windSpeed?.let { ", Wind: $it km/h" } ?: ""
    return "Temp: ${weather.temperature}°C, Condition: ${weather.condition}$wind"
}