package com.yourcast.app.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "temps")
data class Temp(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val day: Double,
    val min: Double,
    val max: Double,
    val night: Double,
    val eve: Double,
    val morn: Double
)