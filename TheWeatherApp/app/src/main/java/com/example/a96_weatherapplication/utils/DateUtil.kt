package com.example.a96_weatherapplication.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object DateUtil {

    //TODO: Change these methods

    @RequiresApi(Build.VERSION_CODES.O)
    fun getDays(days: Int): String {
        val formatter = DateTimeFormatter.ofPattern("EE, MMM yy")
        return LocalDateTime.now().plusDays(days.toLong()).format(formatter).toString()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getDay(days: Int): String {
        val formatter = DateTimeFormatter.ofPattern("EEEE")
        return LocalDateTime.now().plusDays(days.toLong()).format(formatter).toString()
    }
}