package com.sendgrid.deliveryinsightsapp.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sendgrid.deliveryinsightsapp.domain.models.RouteHistory

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
