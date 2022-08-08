package com.yourcast.app.data.repository

import com.yourcast.app.data.model.Direction
import com.yourcast.app.data.remote.ApiResult
import com.yourcast.app.data.remote.ApiService
import com.yourcast.app.data.remote.safeApiCall
import com.yourcast.app.di.qualifier.IoDispatcher
import com.yourcast.app.util.Constants
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchRepository @Inject constructor(
    private val api: ApiService,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) {
    suspend fun search(value: String): Flow<ApiResult<List<Direction>>> = flow {
        emit(ApiResult.Loading)
        val call = safeApiCall(ioDispatcher) {
            api.direct(value, 10, Constants.OPEN_WEATHER_MAP_API_KEY)
        }
        emit(call)
    }
}