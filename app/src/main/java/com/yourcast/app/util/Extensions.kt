package com.yourcast.app.util

import com.yourcast.app.data.network.Response
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import retrofit2.HttpException

object Extensions {

    /** Flow Extensions */
    fun <T> Flow<T>.background(): Flow<T> {
        return this.flowOn(Dispatchers.IO)
    }

    fun <T> Flow<T>.handleNetwork(): Flow<Response<T>> {
        return this.map { data ->
            return@map Response(data = data)
        }.catch { exception ->
            val response = Response<T>().apply {
                when (exception) {
                    is HttpException -> {
                        statusCode = exception.code()
                        msg = exception.message()
                    }
                    else -> {
                        statusCode = -1
                        msg = "Unknown Error happened"
                    }
                }
            }
            emit(response)
        }
    }
}