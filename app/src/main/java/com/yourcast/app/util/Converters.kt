package com.yourcast.app.util

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.yourcast.app.data.model.*

class Converters {

    @TypeConverter
    fun fromHourlyList(list: List<Hourly>?): String? {
        val type = object: TypeToken<List<Hourly>>() {}.type
        return Gson().toJson(list, type)
    }

    @TypeConverter
    fun toHourlyList(value: String): List<Hourly>? {
        val type = object: TypeToken<List<Hourly>>() {}.type
        return Gson().fromJson(value, type)
    }

    @TypeConverter
    fun fromMinutelyList(list: List<Minutely>?): String? {
        val type = object: TypeToken<List<Minutely>>() {}.type
        return Gson().toJson(list, type)
    }

    @TypeConverter
    fun toMinutelyList(value: String): List<Minutely>? {
        val type = object: TypeToken<List<Minutely>>() {}.type
        return Gson().fromJson(value, type)
    }

    @TypeConverter
    fun fromDailyList(list: List<Daily>?): String? {
        val type = object: TypeToken<List<Daily>>() {}.type
        return Gson().toJson(list, type)
    }

    @TypeConverter
    fun toDailyList(value: String): List<Daily>? {
        val type = object: TypeToken<List<Daily>>() {}.type
        return Gson().fromJson(value, type)
    }

    @TypeConverter
    fun fromWeatherList(list: List<Weather>?): String? {
        val type = object: TypeToken<List<Weather>>() {}.type
        return Gson().toJson(list, type)
    }

    @TypeConverter
    fun toWeatherList(value: String): List<Weather>? {
        val type = object: TypeToken<List<Weather>>() {}.type
        return Gson().fromJson(value, type)
    }

    @TypeConverter
    fun fromCurrent(current: Current): String? {
        val type = object: TypeToken<Current>() {}.type
        return Gson().toJson(current, type)
    }

    @TypeConverter
    fun toCurrent(value: String): Current? {
        val type = object: TypeToken<Current>() {}.type
        return Gson().fromJson(value, type)
    }

    @TypeConverter
    fun fromDaily(daily: Daily): String? {
        val type = object: TypeToken<Daily>() {}.type
        return Gson().toJson(daily, type)
    }

    @TypeConverter
    fun toDaily(value: String): Daily? {
        val type = object: TypeToken<Daily>() {}.type
        return Gson().fromJson(value, type)
    }
    @TypeConverter
    fun fromFeelsLike(feelsLike: FeelsLike): String? {
        val type = object: TypeToken<FeelsLike>() {}.type
        return Gson().toJson(feelsLike, type)
    }

    @TypeConverter
    fun toFeelsLike(value: String): FeelsLike? {
        val type = object: TypeToken<FeelsLike>() {}.type
        return Gson().fromJson(value, type)
    }
    @TypeConverter
    fun fromHourly(hourly: Hourly): String? {
        val type = object: TypeToken<Hourly>() {}.type
        return Gson().toJson(hourly, type)
    }

    @TypeConverter
    fun toHourly(value: String): Hourly? {
        val type = object: TypeToken<Hourly>() {}.type
        return Gson().fromJson(value, type)
    }
    @TypeConverter
    fun fromMinutely(current: Minutely): String? {
        val type = object: TypeToken<Minutely>() {}.type
        return Gson().toJson(current, type)
    }

    @TypeConverter
    fun toMinutely(value: String): Minutely? {
        val type = object: TypeToken<Minutely>() {}.type
        return Gson().fromJson(value, type)
    }
    @TypeConverter
    fun fromTemp(temp: Temp): String? {
        val type = object: TypeToken<Temp>() {}.type
        return Gson().toJson(temp, type)
    }

    @TypeConverter
    fun toTemp(value: String): Temp? {
        val type = object: TypeToken<Temp>() {}.type
        return Gson().fromJson(value, type)
    }
    @TypeConverter
    fun fromWeather(weather: Weather): String? {
        val type = object: TypeToken<Weather>() {}.type
        return Gson().toJson(weather, type)
    }

    @TypeConverter
    fun toWeather(value: String): Weather? {
        val type = object: TypeToken<Weather>() {}.type
        return Gson().fromJson(value, type)
    }

}