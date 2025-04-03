package com.sendgrid.deliveryinsightsapp.ui.components


import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun ErrorMessage(error: String) {
    Text(error, color = Color.Red)
}
