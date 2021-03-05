package com.example.a96_weatherapplication.screen.forecastlist


import android.content.SharedPreferences
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a96_weatherapplication.R
import com.example.a96_weatherapplication.model.ForecastContainer
import com.example.a96_weatherapplication.screen.ForecastViewModel
import com.example.a96_weatherapplication.screen.adapters.ForecastAdapter
import com.example.a96_weatherapplication.utils.Prefs
import kotlinx.android.synthetic.main.fragment_forecast.*

//implement SharedPreferences.OnSharedPreferenceChangeListener
class ForecastListFragment : Fragment(), SharedPreferences.OnSharedPreferenceChangeListener  {

    private lateinit var forecastViewModel: ForecastViewModel

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
        forecastViewModel = ViewModelProvider(requireActivity()).get(ForecastViewModel::class.java)
        forecastViewModel.forecastListLiveData.observe(viewLifecycleOwner, Observer {
            createForecastList(it)
        })
        activity?.let {
            val isCelsius = Prefs.retrieveIsCelsiusSetting(it)
            forecastViewModel.fetchForecastInfo(isCelsius)
        }
    }

    private fun createForecastList(forecastContainer: ForecastContainer) {
        val adapter = ForecastAdapter(forecastContainer) { position ->
            val direction = ForecastListFragmentDirections.actionForecastListFragmentToForecastDetailsFragment(position)
            findNavController().navigate(direction)
        }
        weather_recycler_view.layoutManager = LinearLayoutManager(context)
        weather_recycler_view.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        //activity?.getSharedPreferences(Context.MODE_PRIVATE)?.unregisterOnSharedPreferenceChangeListener()
    }

    override fun onPause() {
        super.onPause()
        //activity?.getSharedPreferences(Context.MODE_PRIVATE)?.unregisterOnSharedPreferenceChangeListener()
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

    override fun onDestroyView() {
        super.onDestroyView()
    }

    override fun onSharedPreferenceChanged(sharedPrefs: SharedPreferences?, key: String?) {
    }
}