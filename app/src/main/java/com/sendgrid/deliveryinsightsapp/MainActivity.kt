package com.sendgrid.deliveryinsightsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.room.Room
import com.sendgrid.deliveryinsightsapp.BuildConfig
import com.sendgrid.deliveryinsightsapp.data.local.AppDatabase
import com.sendgrid.deliveryinsightsapp.data.repository.WeatherRepository
import com.sendgrid.deliveryinsightsapp.ui.screens.WeatherScreen
import com.sendgrid.deliveryinsightsapp.ui.viewmodels.WeatherViewModel

class MainActivity : ComponentActivity() {
    private lateinit var viewModel: WeatherViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val repository = provideRepository()
        viewModel = WeatherViewModel(repository)
        setContent {

            WeatherScreen(viewModel = viewModel)
        }
    }

    private fun provideRepository(): WeatherRepository {
        val apiKey = BuildConfig.API_KEY
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "weather_delivery_db"
        ).fallbackToDestructiveMigration().build()
        val historyDao = db.historyDao()
        // Replace "dummyApiKey" with your actual API key
        return WeatherRepository(historyDao, apiKey)
    }
}