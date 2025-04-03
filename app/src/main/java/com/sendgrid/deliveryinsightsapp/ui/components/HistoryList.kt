package com.sendgrid.deliveryinsightsapp.ui.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.sendgrid.deliveryinsightsapp.domain.models.RouteHistory

@Composable
fun HistoryList(history: List<RouteHistory>) {
    LazyColumn {
        items(history) { route ->
            HistoryItem(route)
        }
    }
}