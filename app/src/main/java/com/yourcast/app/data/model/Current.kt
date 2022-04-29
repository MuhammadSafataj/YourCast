package com.yourcast.app.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "currents")
data class Current(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val dt: Long,
    val sunrise: Long,
    val sunset: Long,
    val temp: Double,
    val feels_like: Double,
    val pressure: Long,
    val humidity: Long,
    val dew_point: Double,
    val uvi: Double,
    val clouds: Long,
    val visibility: Long,
    val wind_speed: Double,
    val wind_deg: Long,
    val wind_gust: Double,
    val weather: List<Weather>
)