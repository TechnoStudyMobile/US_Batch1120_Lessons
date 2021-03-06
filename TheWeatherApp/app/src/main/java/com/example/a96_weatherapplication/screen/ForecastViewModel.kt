package com.example.a96_weatherapplication.screen

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.a96_weatherapplication.database.WeatherDatabase
import com.example.a96_weatherapplication.model.ForecastContainer
import com.example.a96_weatherapplication.repository.ForecastContainerRepository

class ForecastViewModel(private val forecastContainerRepository: ForecastContainerRepository) : ViewModel() {

    private val _forecastListLiveData = forecastContainerRepository.forecastListLiveData
    val forecastListLiveData: LiveData<ForecastContainer>
        get() = _forecastListLiveData

    fun getForecastContainer(isCelsius: Boolean) {
        forecastContainerRepository.getForecastContainer(isCelsius)
    }

}

class ForecastViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ForecastViewModel::class.java)) {
            val dao = WeatherDatabase.getDatabase(application).getForecastContainerDao()
            val repository = ForecastContainerRepository(dao)
            @Suppress("UNCHECKED_CAST")
            return ForecastViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}