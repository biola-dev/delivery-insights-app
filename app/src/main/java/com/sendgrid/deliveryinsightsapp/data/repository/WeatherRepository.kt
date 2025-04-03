package com.sendgrid.deliveryinsightsapp.data.repository

import com.sendgrid.deliveryinsightsapp.data.api.RetrofitInstance
import com.sendgrid.deliveryinsightsapp.data.local.HistoryDao
import com.sendgrid.deliveryinsightsapp.domain.models.RouteHistory
import com.sendgrid.deliveryinsightsapp.domain.models.Weather
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WeatherRepository(
    private val historyDao: HistoryDao,
    private val apiKey: String
) {
    suspend fun fetchWeather(location: String): Weather =
        withContext(Dispatchers.IO) {
            RetrofitInstance.weatherService.getWeather(location, apiKey).toWeather()
        }

    suspend fun addRouteHistory(route: RouteHistory) =
        withContext(Dispatchers.IO) {
            historyDao.insertRoute(route)
        }

    suspend fun getHistory() =
        withContext(Dispatchers.IO) {
            historyDao.getLastFiveRoutes()
        }
}