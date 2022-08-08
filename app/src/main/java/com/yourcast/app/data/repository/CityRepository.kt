package com.yourcast.app.data.repository

import com.yourcast.app.data.model.City
import com.yourcast.app.data.remote.ApiService
import com.yourcast.app.data.local.dao.CityDao
import com.yourcast.app.data.remote.ApiResult
import com.yourcast.app.data.remote.safeApiCall
import com.yourcast.app.di.qualifier.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

class CityRepository @Inject constructor(
    private val api: ApiService,
    private val dao: CityDao,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) {

    suspend fun oneCall(lat: Double, lon: Double, apiKey: String): Flow<ApiResult<City>> = flow {
        emit(ApiResult.Loading)
        val call = safeApiCall(Dispatchers.IO) {
            api.oneCall(lat, lon, apiKey)
        }
        emit(call)
    }

    val cities: Flow<List<City>> get() = dao.getCities()

    suspend fun save(city: City) {
        return withContext(ioDispatcher) {
            dao.insert(city)
        }
    }

    suspend fun delete(city: City) {
        return withContext(Dispatchers.IO) {
            dao.delete(city)
        }
    }

    suspend fun update(city: City) {
        return withContext(Dispatchers.IO) {
            dao.update(city)
        }
    }
}