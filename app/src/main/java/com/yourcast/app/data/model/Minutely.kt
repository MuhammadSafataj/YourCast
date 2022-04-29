package com.yourcast.app.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "minutely")
data class Minutely(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val dt: Long,
    val precipitation: Long
)