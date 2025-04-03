package com.sendgrid.deliveryinsightsapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import com.sendgrid.deliveryinsightsapp.ui.components.ErrorMessage
import com.sendgrid.deliveryinsightsapp.ui.components.HistoryList
import com.sendgrid.deliveryinsightsapp.ui.components.LocationInputField
import com.sendgrid.deliveryinsightsapp.ui.components.WeatherButton
import com.sendgrid.deliveryinsightsapp.ui.components.WeatherInfo
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
    val error by viewModel.error.collectAsState()

    // Focus Manager to clear focus on button click
    val focusManager = LocalFocusManager.current

    Scaffold(topBar = {
        TopAppBar(title = { Text("Delivery Insights App") })
    }) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            LocationInputField("Pickup Location", pickup, viewModel::onPickupChanged)
            Spacer(modifier = Modifier.height(8.dp))
            LocationInputField("Drop-off Location", dropOff, viewModel::onDropOffChanged)

            Spacer(modifier = Modifier.height(16.dp))

            WeatherButton {
                viewModel.fetchWeatherData()
                focusManager.clearFocus()  // Remove focus from TextFields after button click
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (error != null) {
                ErrorMessage(error!!)
            } else if (pickupWeather != null && dropOffWeather != null) {
                WeatherInfo(pickupWeather!!, dropOffWeather!!, recommendation)
            }

            Spacer(modifier = Modifier.height(16.dp))
            Text("Recent Routes", style = MaterialTheme.typography.titleMedium)
            HistoryList(history)
        }
    }
}
