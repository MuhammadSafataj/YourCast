package com.yourcast.app.data.network

import com.yourcast.app.data.model.Current
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("weather")
    suspend fun getCurrent(@Query("q") city: String, @Query("appid") apiKey: String): Current
}