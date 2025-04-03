package com.sendgrid.deliveryinsightsapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sendgrid.deliveryinsightsapp.domain.models.RouteHistory


@Database(entities = [RouteHistory::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun historyDao(): HistoryDao
}