package com.sendgrid.deliveryinsightsapp.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "route_history")
data class RouteHistory(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val pickup: String,
    val dropOff: String,
    val timestamp: Long = System.currentTimeMillis()
)