package com.matrimony.rvrmatrimony.data.remote

sealed class ApiResponse<T> {
    data class Success<T>(val data: T) : ApiResponse<T>()
    data class Error<T>(val message: String) : ApiResponse<T>()
    class Loading<T> : ApiResponse<T>()
}