package com.yourcast.app.data.local.dao

import androidx.room.*
import com.yourcast.app.data.model.City
import kotlinx.coroutines.flow.Flow

@Dao
interface CityDao {

    @Query("SELECT * fROM cities")
    fun getCities(): Flow<List<City>>

    @Insert
    suspend fun insert(city: City)

    @Delete
    suspend fun delete(city: City)

    @Update
    suspend fun update(city: City)
}