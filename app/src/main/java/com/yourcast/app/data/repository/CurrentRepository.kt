package com.yourcast.app.data.repository

import com.yourcast.app.data.model.Current
import com.yourcast.app.data.network.ApiService
import com.yourcast.app.data.network.Response
import com.yourcast.app.data.persistence.dao.CurrentDao
import com.yourcast.app.util.Constants
import com.yourcast.app.util.Extensions.background
import com.yourcast.app.util.Extensions.handleNetwork
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CurrentRepository @Inject constructor(
    private val api: ApiService,
) {

    fun getCurrent(city: String): Flow<Response<Current>> {
        return flow {
            val result = api.getCurrent(city, Constants.OPEN_WEATHER_MAP_API_KEY)
            emit(result)
        }.background().handleNetwork()
    }

}