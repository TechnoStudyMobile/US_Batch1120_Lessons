package com.example.a96_weatherapplication.screen.forecastdetails

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.a96_weatherapplication.R
import com.example.a96_weatherapplication.screen.ForecastViewModel
import kotlinx.android.synthetic.main.fragment_forecast_details.*

class ForecastDetailsFragment : Fragment() {

    private val args: ForecastDetailsFragmentArgs by navArgs()
    private lateinit var forecastViewModel: ForecastViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.fragment_details_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.settings -> {
                val direction = ForecastDetailsFragmentDirections.actionForecastDetailsFragmentToSettingsFragment()
                findNavController().navigate(direction)
            }
            R.id.share -> {
                //TODO: Share the Forecast details as a text
            }
        }
        return super.onOptionsItemSelected(item)
    }
}