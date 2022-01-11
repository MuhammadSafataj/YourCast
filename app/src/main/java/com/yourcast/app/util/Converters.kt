package com.yourcast.app.util

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.yourcast.app.data.model.Coord

object Converters {

    @TypeConverter
    fun fromCoord(coord: Coord): String {
        return Gson().toJson(coord)
    }

    @TypeConverter
    fun toCoord(string: String): Coord {
        val type = object : TypeToken<Coord>() {}.type
        return Gson().fromJson<Coord>(string, type)
    }
}