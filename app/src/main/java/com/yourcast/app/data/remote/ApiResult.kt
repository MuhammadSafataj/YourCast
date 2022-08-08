package com.yourcast.app.data.remote

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

sealed class ApiResult<out T> {
    data class Success<out R>(val data: R): ApiResult<R>()
    data class GenericError(val code: Int? = null, val message: String?): ApiResult<Nothing>()
    object NetworkError : ApiResult<Nothing>()
    object Loading : ApiResult<Nothing>()
}

suspend fun <T> safeApiCall(dispatcher: CoroutineDispatcher, apiCall: suspend () -> T): ApiResult<T> {
    return withContext(dispatcher) {
        try {
            ApiResult.Success(apiCall.invoke())
        } catch (throwable: Throwable) {
            when (throwable) {
                is IOException -> ApiResult.NetworkError
                is HttpException -> ApiResult.GenericError(throwable.code(), throwable.message())
                else -> ApiResult.GenericError(null, null)
            }
        }
    }
}
