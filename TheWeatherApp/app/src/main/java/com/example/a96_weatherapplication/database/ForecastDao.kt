package com.example.a96_weatherapplication.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.a96_weatherapplication.model.ForecastContainer

@Dao
interface ForecastDao {
    @Query("SELECT * FROM forecastContainers")
    fun getForecastContainer() : List<ForecastContainer>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(forecastContainer: ForecastContainer)

    @Query("DELETE FROM forecastContainers")
    fun deleteAll()
}