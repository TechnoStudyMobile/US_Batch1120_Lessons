package com.example.a96_weatherapplication.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.a96_weatherapplication.WEATHER_API_KEY
import com.example.a96_weatherapplication.database.ForecastContainerDao
import com.example.a96_weatherapplication.model.ForecastContainer
import com.example.a96_weatherapplication.network.ForecastService
import com.example.a96_weatherapplication.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ForecastContainerRepository(private val dao: ForecastContainerDao) {

    val forecastListLiveData: LiveData<ForecastContainer> = dao.getForecastContainer()

    fun getForecastContainer(isCelsius: Boolean) {
        fetchForecastContainer(isCelsius)
    }

    private fun insertToDatabase(forecastContainer: ForecastContainer) {
        // Delete everything
        // Save to db
        Thread {
            dao.deleteAll()
            dao.insert(forecastContainer)
        }.start()
    }

    private fun fetchForecastContainer(isCelsius: Boolean) {
        val forecastService = RetrofitClient.retrofit?.create(ForecastService::class.java)
        val units = if (isCelsius) "M" else "I"
        val forecastCall = forecastService?.getForecast("7", "38.123", "-78.543", units, WEATHER_API_KEY)

        forecastCall?.enqueue(object : Callback<ForecastContainer> {
            override fun onResponse(
                call: Call<ForecastContainer>,
                response: Response<ForecastContainer>
            ) {
                Log.d("WeatherApp", response.message() + response.body().toString())
                val forecastContainer: ForecastContainer? = response.body()

                //Save to db
                forecastContainer?.let {
                    insertToDatabase(it)
                }
            }

            override fun onFailure(call: Call<ForecastContainer>, t: Throwable) {
                Log.d("WeatherApp", t.localizedMessage)
            }
        })
    }
}