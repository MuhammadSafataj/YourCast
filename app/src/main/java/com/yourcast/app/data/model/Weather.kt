package com.yourcast.app.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weathers")
data class Weather(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val main: String,
    val description: String,
    val icon: String
)
