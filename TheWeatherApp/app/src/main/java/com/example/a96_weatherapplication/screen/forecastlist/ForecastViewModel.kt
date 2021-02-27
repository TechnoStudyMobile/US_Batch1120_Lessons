package com.example.a96_weatherapplication.screen.forecastlist

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.a96_weatherapplication.WEATHER_API_KEY
import com.example.a96_weatherapplication.model.ForecastDummy
import com.example.a96_weatherapplication.model.ForecastResponse
import com.example.a96_weatherapplication.network.ForecastService
import com.example.a96_weatherapplication.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ForecastViewModel : ViewModel() {

    private val _forecastListLiveData = MutableLiveData<ForecastResponse>()
    val forecastListLiveData: LiveData<ForecastResponse>
        get() = _forecastListLiveData

    fun fetchForecastInfo(isCelsius: Boolean) {
        val forecastService = RetrofitClient.retrofit?.create(ForecastService::class.java)
        val units = if (isCelsius) "M" else "I"
        val forecastCall = forecastService?.getForecast("7", "38.123", "-78.543", units, WEATHER_API_KEY)

        forecastCall?.enqueue(object : Callback<ForecastResponse> {
            override fun onResponse(
                call: Call<ForecastResponse>,
                response: Response<ForecastResponse>
            ) {
                Log.d("WeatherApp", response.message() + response.body().toString())
                val forecastResponse: ForecastResponse? = response.body()
                _forecastListLiveData.value = forecastResponse
            }

            override fun onFailure(call: Call<ForecastResponse>, t: Throwable) {
                Log.d("WeatherApp", t.localizedMessage)
            }
        })
    }
}