package com.yourcast.app.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "feels_like")
data class FeelsLike(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val day: Double,
    val night: Double,
    val eve: Double,
    val morn: Double
)