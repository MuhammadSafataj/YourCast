package com.yourcast.app.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "daily")
data class Daily(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val dt: Long,
    val sunrise: Long,
    val sunset: Long,
    val moonrise: Long,
    val moonset: Long,
    val moon_phase: Double,
    val temp: Temp,
    val feels_like: FeelsLike,
    val pressure: Long,
    val humidity: Long,
    val dew_point: Double,
    val wind_speed: Double,
    val wind_deg: Long,
    val wind_gust: Double,
    val weather: List<Weather>,
    val clouds: Long,
    val pop: Double,
    val uvi: Double,
    val rain: Double?,
    val snow: Double?
)
