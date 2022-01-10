package com.yourcast.app.data.model

import androidx.room.Entity

@Entity
data class Current(
    val coord: Coord,
    val weather: ArrayList<Weather>,
    val base: String,
    val main: Main,
    val visibility: Int,
    val wind: Wind,
    var clouds: Clouds? = null,
    var rain: Rain? = null,
    var snow: Snow? = null,
    val dt: Int,
    val sys: Sys,
    val timezone: Int,
    val id: Int,
    val name: String,
    val cod: Int
)
