package com.yourcast.app.data.repository

import com.yourcast.app.data.network.ApiService
import javax.inject.Inject

class ForecastRepository @Inject constructor(
    private val apiService: ApiService,

) {
}