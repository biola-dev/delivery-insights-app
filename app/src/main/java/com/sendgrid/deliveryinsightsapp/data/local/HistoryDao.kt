package com.sendgrid.deliveryinsightsapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sendgrid.deliveryinsightsapp.domain.models.RouteHistory


@Dao
interface HistoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRoute(route: RouteHistory)

    @Query("SELECT * FROM route_history ORDER BY timestamp DESC LIMIT 5")
    suspend fun getLastFiveRoutes(): List<RouteHistory>
}