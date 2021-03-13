package com.example.a96_weatherapplication.repository

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.a96_weatherapplication.WEATHER_API_KEY
import com.example.a96_weatherapplication.database.ForecastContainerDao
import com.example.a96_weatherapplication.model.ForecastContainer
import com.example.a96_weatherapplication.network.ForecastService
import com.example.a96_weatherapplication.network.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class ForecastContainerRepository(private val dao: ForecastContainerDao) {

    val forecastListLiveData: LiveData<ForecastContainer> = dao.getForecastContainer()

    suspend fun getForecastContainer(isCelsius: Boolean) {
        withContext(Dispatchers.IO) {
            fetchForecastContainer(isCelsius)
        }
    }

    private fun fetchForecastContainer(isCelsius: Boolean) {
        val forecastService = RetrofitClient.retrofit?.create(ForecastService::class.java)
        val units = if (isCelsius) "M" else "I"
        val forecastCall = forecastService?.getForecast("7", "38.123", "-78.543", units, WEATHER_API_KEY)

        try {
            val response = forecastCall?.execute()
            val forecastContainer = response?.body()
            forecastContainer?.let {
                Log.d("WeatherApp", response.message() + forecastContainer.toString())
                insertToDatabase(it)
            }
            //TODO: Handle error cases when forecastContainer is null
        } catch (ex: Exception) {
            Log.d("WeatherApp", ex.toString())
        }
    }

    fun insertToDatabase(forecastContainer: ForecastContainer) {
        // Delete everything
        // Save to db
        GlobalScope.launch {
            withContext(Dispatchers.IO) {
                dao.deleteAll()
                dao.insert(forecastContainer)
            }
        }
    }

}