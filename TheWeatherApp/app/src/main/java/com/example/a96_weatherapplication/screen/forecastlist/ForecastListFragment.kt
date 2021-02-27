package com.example.a96_weatherapplication.screen.forecastlist


import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a96_weatherapplication.R
import com.example.a96_weatherapplication.model.ForecastResponse
import com.example.a96_weatherapplication.screen.adapters.ForecastAdapter
import com.example.a96_weatherapplication.utils.Prefs
import kotlinx.android.synthetic.main.fragment_forecast.*

//implement SharedPreferences.OnSharedPreferenceChangeListener
class ForecastListFragment : Fragment() {

    private lateinit var forecastViewModel: ForecastViewModel

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
        forecastViewModel = ViewModelProvider(requireActivity()).get(ForecastViewModel::class.java)
        forecastViewModel.forecastListLiveData.observe(viewLifecycleOwner, Observer {
            createForecastList(it)
        })
        forecastViewModel.fetchForecastInfo()
        activity?.let {
            Prefs.setIsNotificationEnabledSetting(it,false)
        }
    }

    private fun createForecastList(forecastResponse: ForecastResponse) {
        val adapter = ForecastAdapter(forecastResponse) { position ->
            val direction = ForecastListFragmentDirections.actionForecastListFragmentToForecastDetailsFragment(position)
            findNavController().navigate(direction)
        }
        weather_recycler_view.layoutManager = LinearLayoutManager(context)
        weather_recycler_view.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        //context?.getSharedPreferences("File_name",Context.MODE_PRIVATE)?.unregisterOnSharedPreferenceChangeListener()
    }

    override fun onPause() {
        super.onPause()
        //context?.getSharedPreferences("File_name",Context.MODE_PRIVATE)?.unregisterOnSharedPreferenceChangeListener()
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