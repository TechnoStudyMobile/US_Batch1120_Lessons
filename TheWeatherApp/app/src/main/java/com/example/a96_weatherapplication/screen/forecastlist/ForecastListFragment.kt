package com.example.a96_weatherapplication.screen.forecastlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a96_weatherapplication.R
import com.example.a96_weatherapplication.model.ForecastResponse
import com.example.a96_weatherapplication.screen.adapters.WeatherAdapter
import kotlinx.android.synthetic.main.fragment_forecast.*

class ForecastListFragment : Fragment() {

    private lateinit var forecastViewModel: ForecastViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_forecast, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        forecastViewModel = ViewModelProvider(this).get(ForecastViewModel::class.java)

        forecastViewModel.forecastListLiveData.observe(viewLifecycleOwner, Observer {
            createWeatherList(it)
        })

        forecastViewModel.fetchForecastInfo()
    }

    private fun createWeatherList(forecastResponse: ForecastResponse) {
        val adapter = WeatherAdapter(forecastResponse) { position ->
            //Navigate
            findNavController().navigate(R.id.action_forecastListFragment_to_forecastDetailsFragment)
        }
        weather_recycler_view.layoutManager = LinearLayoutManager(context)
        weather_recycler_view.adapter = adapter
    }
}