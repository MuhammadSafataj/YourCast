package com.yourcast.app.data.remote

sealed class ApiResult<out R> {
    data class Success<out T>(val data: T): ApiResult<T>()
    data class Error(val exception: Exception): ApiResult<Nothing>()
}
