package com.yourcast.app.data.repository

import com.yourcast.app.data.model.City
import com.yourcast.app.data.remote.ApiService
import com.yourcast.app.data.local.dao.CityDao
import com.yourcast.app.data.remote.ApiResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.lang.Exception
import javax.inject.Inject

class CityRepository @Inject constructor(
    private val api: ApiService,
    private val dao: CityDao
) {

    fun oneCall(lat: Double, lon: Double, apiKey: String): Flow<ApiResult<City>> {
        return flow {
            try {
                val result = api.oneCall(lat, lon, apiKey)
                emit(ApiResult.Success(result))
            } catch (exception: Exception) {
                emit(ApiResult.Error(exception))
            }
        }.flowOn(Dispatchers.IO)
    }

    val cities: Flow<List<City>> get() = dao.getCities()

    fun saveCity(city: City) {
        dao.insert(city)
    }
}