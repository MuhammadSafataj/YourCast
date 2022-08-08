package com.yourcast.app.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cities")
data class City(
    @PrimaryKey(autoGenerate = true) val id: Int,
    var name: String,
    var country: String,
    val lat: Double,
    val lon: Double,
    val timezone: String,
    val timezone_offset: Long,
    var current: Current,
    var minutely: List<Minutely>? = null,
    var hourly: List<Hourly>? = null,
    var daily: List<Daily>? = null
)
