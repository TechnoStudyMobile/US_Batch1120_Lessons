package com.example.a96_weatherapplication.screen.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.a96_weatherapplication.R
import com.example.a96_weatherapplication.model.ForecastDummy
import com.example.a96_weatherapplication.model.ForecastResponse
import kotlinx.android.synthetic.main.forecast_single_view.view.*

class WeatherAdapter(private val forecastResponse: ForecastResponse, val onClick: (position: Int)-> Unit) :
    RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.forecast_single_view, parent, false)
        return WeatherViewHolder(view)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        holder.itemView.run {
            //icon_image_view.setImageResource(forecastResponse.forecastList[position].weather.code)
            day_of_week_text_view.text = forecastResponse.forecastList[position].datetime
            forecast_text_view.text = forecastResponse.forecastList[position].weather.description
            textView_single_view_temp_high.text = forecastResponse.forecastList[position].maxTemp.toString()
            textView_single_view_temp_low.text = forecastResponse.forecastList[position].minTemp.toString()
        }
    }

    override fun getItemCount(): Int {
        return forecastResponse.forecastList.size
    }

    inner class WeatherViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener {
                onClick.invoke(adapterPosition)
            }
        }
    }
}