package com.example.a96_weatherapplication.screen

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.a96_weatherapplication.WEATHER_API_KEY
import com.example.a96_weatherapplication.model.ForecastContainer
import com.example.a96_weatherapplication.network.ForecastService
import com.example.a96_weatherapplication.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ForecastViewModel : ViewModel() {

    private val _forecastListLiveData = MutableLiveData<ForecastContainer>()
    val forecastListLiveData: LiveData<ForecastContainer>
        get() = _forecastListLiveData

    fun fetchForecastInfo(isCelsius: Boolean) {
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
                _forecastListLiveData.value = forecastContainer
            }

            override fun onFailure(call: Call<ForecastContainer>, t: Throwable) {
                Log.d("WeatherApp", t.localizedMessage)
            }
        })
    }
}