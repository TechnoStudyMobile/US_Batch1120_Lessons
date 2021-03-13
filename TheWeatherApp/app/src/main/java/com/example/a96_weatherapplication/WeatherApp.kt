package com.example.a96_weatherapplication

import android.app.Application
import com.example.a96_weatherapplication.utils.NotificationUtil

class WeatherApp : Application() {

    override fun onCreate() {
        super.onCreate()

        //Create notification channels
        NotificationUtil.createNotificationChannel(applicationContext)
    }
}