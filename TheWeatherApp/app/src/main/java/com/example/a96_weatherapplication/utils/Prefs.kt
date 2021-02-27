package com.example.a96_weatherapplication.utils

import android.app.Activity
import android.content.Context
import com.example.a96_weatherapplication.IS_CELSIUS_DEFAULT_SETTINGS_VALUE
import com.example.a96_weatherapplication.IS_CELSIUS_SETTING_PREF_KEY

object Prefs {
    fun retrieveIsCelsiusSetting(activity: Activity): Boolean {
        val sharedPref = activity.getPreferences(Context.MODE_PRIVATE)
        return sharedPref.getBoolean(IS_CELSIUS_SETTING_PREF_KEY, IS_CELSIUS_DEFAULT_SETTINGS_VALUE)
    }

    fun setIsCelsiusSetting(activity: Activity, value: Boolean) {
        val sharedPref = activity.getPreferences(Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putBoolean(IS_CELSIUS_SETTING_PREF_KEY, value)
        editor.apply()
    }
}

/*
//Save
val sharedPref = context?.getSharedPreferences(
    "com.example.a96_weather.sharedPrefFile",
    Context.MODE_PRIVATE
)
val sharedPref2 = activity?.getPreferences(Context.MODE_PRIVATE)

//Save
val editor = sharedPref?.edit()
editor?.putBoolean(IS_METRIC_SETTING_PREF_KEY, false)
editor?.apply()

//Retrieve
val value = sharedPref?.getBoolean(IS_METRIC_SETTING_PREF_KEY, IS_METRIC_DEFAULT_SETTINGS_VALUE)

//Remove
editor?.remove(IS_METRIC_SETTING_PREF_KEY)
editor?.apply()

//Clear all
editor?.clear()
editor?.apply()
*/

