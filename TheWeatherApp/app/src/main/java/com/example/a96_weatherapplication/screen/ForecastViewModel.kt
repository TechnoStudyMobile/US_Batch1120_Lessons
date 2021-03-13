package com.example.a96_weatherapplication.screen

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.a96_weatherapplication.database.WeatherDatabase
import com.example.a96_weatherapplication.model.ForecastContainer
import com.example.a96_weatherapplication.repository.ForecastContainerRepository
import kotlinx.coroutines.*

class ForecastViewModel(private val forecastContainerRepository: ForecastContainerRepository) : ViewModel() {

    private val _forecastListLiveData = forecastContainerRepository.forecastListLiveData
    val forecastListLiveData: LiveData<ForecastContainer>
        get() = _forecastListLiveData

    var result = 0

    fun getForecastContainer(isCelsius: Boolean) {
        viewModelScope.launch {
            forecastContainerRepository.getForecastContainer(isCelsius)
            //This line not going to run unless upper line finish

            // Switch to another threading pool using dispatcher
            withContext(Dispatchers.Default) {

            }
            //Update something - which will wait previous lines to finish
        }
        doCalculation()
    }

    fun doCalculation() {
        viewModelScope.launch {
            //Coroutine is syncronous

            //Main thread
            result = 0

            //Switch to another coroutine
            // and run asyncrounsly-parellel
            val action = async {
                calculateMath()
            }

            val action2 = async {
                //other operations
            }

            Log.d("Calculation", "Some other operations: happening") //1
            //Some other operations
            delay(1000L)

            action.await()
            // Do something with calculateMathResult
            Log.d("Calculation", "${result} is finished") //2

            action2.await()
        }
    }

    private suspend fun calculateMath() {

        //Main-UI thread
        Dispatchers.Main

        // Fetching info from internet - Db - file operation
        Dispatchers.IO

        // CPU intensive work - parsing - math calculations
        Dispatchers.Default

        withContext(Dispatchers.Default) {
            Log.d("Calculation", "calculateMath started") //3
            delay(3000L)
            result = 100
            Log.d("Calculation", "calculateMath ended") //4
        }
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