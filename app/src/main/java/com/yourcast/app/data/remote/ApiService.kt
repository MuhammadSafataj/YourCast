package com.yourcast.app.data.remote

import com.yourcast.app.data.model.City
import com.yourcast.app.data.model.Direction
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("data/2.5/onecall")
    suspend fun oneCall(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") apiKey: String,
        @Query("units") units: String = "metric"
    ): City

    @GET("geo/1.0/direct")
    suspend fun direct(
        @Query("q") name: String,
        @Query("limit") limit: Int,
        @Query("appid") apiKey: String
    ): List<Direction>
}