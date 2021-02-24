package com.example.a96_weatherapplication.screen.forecastdetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.a96_weatherapplication.KEY_DAILY_FORECAST_DETAILS
import com.example.a96_weatherapplication.R
import com.example.a96_weatherapplication.model.Forecast
import com.example.a96_weatherapplication.screen.forecastlist.ForecastViewModel
import kotlinx.android.synthetic.main.fragment_forecast_details.*

class ForecastDetailsFragment : Fragment() {

    private val args: ForecastDetailsFragmentArgs by navArgs()
    private lateinit var forecastViewModel: ForecastViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        forecastViewModel = ViewModelProvider(requireActivity()).get(ForecastViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_forecast_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        forecastViewModel.forecastListLiveData.observe(viewLifecycleOwner, Observer {
            it?.let {
                forecast_details_text_view.text = it.forecastList.getOrNull(args.position)?.toString()
            }
        })
    }
}