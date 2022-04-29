package com.yourcast.app.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.yourcast.app.data.model.City
import kotlinx.coroutines.flow.Flow

@Dao
interface CityDao {

    @Query("SELECT * fROM cities")
    fun getCities(): Flow<List<City>>

    @Insert
    fun insert(city: City)
}