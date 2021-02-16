package com.example.a96_weatherapplication.screen.forecastlist

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a96_weatherapplication.model.ForecastDummy
import com.example.a96_weatherapplication.R
import com.example.a96_weatherapplication.screen.adapters.WeatherAdapter
import com.example.a96_weatherapplication.utils.DateUtil.getDay
import com.example.a96_weatherapplication.utils.DateUtil.getDays
import kotlinx.android.synthetic.main.fragment_forecast.*

class ForecastListFragment : Fragment() {

    lateinit var recyclerView: RecyclerView
    private lateinit var forecastViewModel: ForecastViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_forecast, container, false)
    }


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.weatherRecycler)
        forecastViewModel = ViewModelProvider(this).get(ForecastViewModel::class.java)

        forecastViewModel.forecastListLiveData.observe(viewLifecycleOwner, Observer {
            //If the Fragment is visible - on foreground.
            createWeatherList(it)
        })

        forecastViewModel.populateAnimalsList()
    }


    private fun createWeatherList(list: List<ForecastDummy>) {
        //UI related
        val adapter =
            WeatherAdapter(
                list
            )
        val layoutManager = LinearLayoutManager(context)

        weatherRecycler.layoutManager = layoutManager
        weatherRecycler.adapter = adapter
    }
}