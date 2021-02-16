package com.example.a96_weatherapplication.screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.a96_weatherapplication.R
import com.example.a96_weatherapplication.screen.forecastlist.ForecastListFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val forecastListFragment = ForecastListFragment();
        val manager = supportFragmentManager
        manager.beginTransaction()
            .replace(R.id.fragment_container, forecastListFragment)
            .setReorderingAllowed(true)
            .addToBackStack(null)
            .commit()
    }
}