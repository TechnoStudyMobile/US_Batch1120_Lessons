package com.example.a96_weatherapplication.screen.forecastlist


import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a96_weatherapplication.R
import com.example.a96_weatherapplication.WeatherApp
import com.example.a96_weatherapplication.model.ForecastContainer
import com.example.a96_weatherapplication.screen.ForecastViewModel
import com.example.a96_weatherapplication.screen.ForecastViewModelFactory
import com.example.a96_weatherapplication.screen.adapters.ForecastAdapter
import com.example.a96_weatherapplication.utils.NotificationUtil
import com.example.a96_weatherapplication.utils.Prefs
import kotlinx.android.synthetic.main.fragment_forecast.*
import kotlinx.coroutines.launch

class ForecastListFragment : Fragment()  {

    private lateinit var forecastViewModel: ForecastViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = ForecastViewModelFactory(requireActivity().application)
        forecastViewModel = ViewModelProvider(requireActivity(), factory).get(ForecastViewModel::class.java)

        val isCelsius = Prefs.retrieveIsCelsiusSetting(requireActivity())
        forecastViewModel.getForecastContainer(isCelsius)
    }

    //TODO: Fix this
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_forecast, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        forecastViewModel.forecastListLiveData.observe(viewLifecycleOwner, Observer {
            it?.let {
                createForecastList(it)
                //Fire a notification
                it.forecastList.firstOrNull()?.let { forecast ->
                    //in-app notification
                    NotificationUtil.fireTodayForecastNotification(requireContext(), forecast)
                }
            }
        })
    }

    private fun createForecastList(forecastContainer: ForecastContainer) {
        val adapter = ForecastAdapter(forecastContainer) { position ->
            val direction = ForecastListFragmentDirections.actionForecastListFragmentToForecastDetailsFragment(position)
            findNavController().navigate(direction)
        }
        weather_recycler_view.layoutManager = LinearLayoutManager(context)
        weather_recycler_view.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.fragment_list_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.settings -> {
                val direction =
                    ForecastListFragmentDirections.actionForecastListFragmentToSettingsFragment()
                findNavController().navigate(direction)
            }
            R.id.map_location -> {
                //TODO: Open Google maps with user's location
            }
        }
        return super.onOptionsItemSelected(item)
    }
}