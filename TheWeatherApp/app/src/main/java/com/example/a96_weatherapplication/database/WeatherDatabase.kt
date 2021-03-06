package com.example.a96_weatherapplication.database

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.a96_weatherapplication.WEATHER_ROOM_DATABASE_NAME
import com.example.a96_weatherapplication.model.ForecastContainer

@Database(entities = [ForecastContainer::class], version = 1)
@TypeConverters(ForecastListConverter::class)
abstract class WeatherDatabase : RoomDatabase() {

    abstract fun getForecastContainerDao(): ForecastContainerDao

    companion object {
        @Volatile
        private var INSTANCE: WeatherDatabase? = null

        fun getDatabase(application: Application): WeatherDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    application.applicationContext,
                    WeatherDatabase::class.java,
                    WEATHER_ROOM_DATABASE_NAME
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                instance
            }
        }
    }
}