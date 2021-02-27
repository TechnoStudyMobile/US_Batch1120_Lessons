package com.example.a96_weatherapplication.screen.adapters

import android.os.AsyncTask
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.a96_weatherapplication.R
import com.example.a96_weatherapplication.model.Forecast
import com.example.a96_weatherapplication.model.ForecastResponse
import kotlinx.android.synthetic.main.forecast_next_day_item.view.*
import kotlinx.android.synthetic.main.forecast_today_item.view.*

class ForecastAdapter(
    private val forecastResponse: ForecastResponse,
    val onClick: (position: Int) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val TODAY_FORECAST_ITEM = 0
    private val NEXT_DAY_FORECAST_ITEM = 1

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) TODAY_FORECAST_ITEM else NEXT_DAY_FORECAST_ITEM
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == TODAY_FORECAST_ITEM) {
            //ForecastTodayViewHolder
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.forecast_today_item, parent, false)
            ForecastTodayViewHolder(view, onClick)
        } else {
            //ForecastNextDayViewHolder
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.forecast_next_day_item, parent, false)
            ForecastNextDayViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ForecastTodayViewHolder -> holder.bind(forecastResponse.forecastList[position])
            is ForecastNextDayViewHolder -> holder.bind(position)
        }
    }

    override fun getItemCount(): Int {
        return forecastResponse.forecastList.size
    }

    inner class ForecastNextDayViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener {
                onClick.invoke(adapterPosition)
            }
        }

        fun bind(position: Int) {
            itemView.run {
                //icon_image_view.setImageResource(forecastResponse.forecastList[position].weather.code)
                day_of_week_text_view.text = forecastResponse.forecastList[position].datetime
                forecast_text_view.text =
                    forecastResponse.forecastList[position].weather.description
                textView_single_view_temp_high.text =
                    forecastResponse.forecastList[position].maxTemp.toString()
                textView_single_view_temp_low.text =
                    forecastResponse.forecastList[position].minTemp.toString()
            }
        }
    }
}

class ForecastTodayViewHolder(itemView: View, val onClick: (position: Int) -> Unit) : RecyclerView.ViewHolder(itemView) {

    init {
        itemView.setOnClickListener {
            onClick.invoke(adapterPosition)
        }
    }

    fun bind(forecast: Forecast) {
        itemView.run {
            //icon_image_view.setImageResource(forecastResponse.forecastList[position].weather.code)
            today_day_of_week_text_view.text = forecast.datetime
            today_forecast_text_view.text = forecast.weather.description
            today_textView_single_view_temp_high.text = forecast.maxTemp.toString()
            today_textView_single_view_temp_low.text = forecast.minTemp.toString()
        }
    }
}