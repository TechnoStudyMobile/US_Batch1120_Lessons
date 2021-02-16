package com.example.a96_weatherapplication.screen.forecastlist

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.a96_weatherapplication.R
import com.example.a96_weatherapplication.model.Forecast
import com.example.a96_weatherapplication.model.ForecastDummy
import com.example.a96_weatherapplication.utils.DateUtil

class ForecastViewModel : ViewModel() {

    private val forecastList = mutableListOf<ForecastDummy>()

    private val _forecastListLiveData = MutableLiveData<List<ForecastDummy>>()
    val forecastListLiveData: LiveData<List<ForecastDummy>>
        get() = _forecastListLiveData

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SimpleDateFormat")
    fun populateAnimalsList() {

        forecastList.add(
            ForecastDummy(
                imageID = R.drawable.forecast,
                textViewWeekDay = DateUtil.getDay(0),
                textViewForecast = "Forecast",
                textViewFTempHight = "0°",
                textViewFTempLow = "-2°"
            )
        )
        forecastList.add(
            ForecastDummy(
                imageID = R.drawable.sunny,
                textViewWeekDay = DateUtil.getDay(1),
                textViewForecast = "Snow",
                textViewFTempHight = "-2°",
                textViewFTempLow = "-5°"
            )
        )
        forecastList.add(
            ForecastDummy(
                imageID = R.drawable.rain,
                textViewWeekDay = DateUtil.getDay(2),
                textViewForecast = "Rain",
                textViewFTempHight = "+35°"
            )
        )
        forecastList.add(
            ForecastDummy(
                imageID = R.drawable.cloudy,
                textViewWeekDay = DateUtil.getDay(3),
                textViewForecast = "Cloudy",
                textViewFTempLow = "-100°"
            )
        )
        forecastList.add(
            ForecastDummy(
                imageID = R.drawable.rain,
                textViewWeekDay = DateUtil.getDay(4),
                textViewForecast = "Rain",
                textViewFTempHight = "+3°"
            )
        )
        forecastList.add(
            ForecastDummy(
                imageID = R.drawable.snow,
                textViewWeekDay = DateUtil.getDay(5),
                textViewForecast = "Snow",
                textViewFTempHight = "-2°",
                textViewFTempLow = "-5°"
            )
        )
        forecastList.add(
            ForecastDummy(
                imageID = R.drawable.sunny,
                textViewWeekDay = DateUtil.getDay(6),
                textViewForecast = "Sunny",
                textViewFTempHight = "-1°",
                textViewFTempLow = "-5°"
            )
        )
        forecastList.add(
            ForecastDummy(
                imageID = R.drawable.sunny,
                textViewWeekDay = DateUtil.getDays(7),
                textViewForecast = "Sunny",
                textViewFTempHight = "0°",
                textViewFTempLow = "-2°"
            )
        )
        forecastList.add(
            ForecastDummy(
                imageID = R.drawable.snow,
                textViewWeekDay = DateUtil.getDays(8),
                textViewForecast = "Cloudy",
                textViewFTempHight = "-1°",
                textViewFTempLow = "-5°"
            )
        )
        forecastList.add(
            ForecastDummy(
                imageID = R.drawable.cloudy,
                textViewWeekDay = DateUtil.getDays(9),
                textViewForecast = "Cloudy",
                textViewFTempHight = "-1°",
                textViewFTempLow = "-5°"
            )
        )
        forecastList.add(
            ForecastDummy(
                imageID = R.drawable.cloudy,
                textViewWeekDay = DateUtil.getDays(10),
                textViewForecast = "Cloudy",
                textViewFTempHight = "-1°",
                textViewFTempLow = "-5°"
            )
        )
        forecastList.add(
            ForecastDummy(
                imageID = R.drawable.cloudy,
                textViewWeekDay = DateUtil.getDays(11),
                textViewForecast = "Cloudy",
                textViewFTempHight = "-1°",
                textViewFTempLow = "-5°"
            )
        )
        forecastList.add(
            ForecastDummy(
                imageID = R.drawable.snow,
                textViewWeekDay = DateUtil.getDays(12),
                textViewForecast = "Snow",
                textViewFTempHight = "-2°",
                textViewFTempLow = "-5°"
            )
        )
        forecastList.add(
            ForecastDummy(
                imageID = R.drawable.sunny,
                textViewWeekDay = DateUtil.getDays(13),
                textViewForecast = "Sunny",
                textViewFTempHight = "-1°",
                textViewFTempLow = "-5°"
            )
        )

        _forecastListLiveData.value = forecastList

    }

}