package com.example.a96_weatherapplication.network

import com.example.a96_weatherapplication.model.ForecastResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ForecastService {
    //https://api.weatherbit.io/v2.0/forecast/daily?days=14&lat=38.123&lon=-78.543&key=df23d10ee9a24be6a200e500c793edd5

    @GET("daily")
    fun getForecast(@Query("days") days: String,
                    @Query("lat") lat:String,
                    @Query("lon") lon:String,
                    @Query("key") key:String,
    ): Call<ForecastResponse>
}