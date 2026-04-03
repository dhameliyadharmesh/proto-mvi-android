package com.proto.mvi.util

sealed class ResultWrapper<out T> {
	data class Success<out T>(val value: T) : ResultWrapper<T>()
	data class ApiError(val code: Int, val message: String?) : ResultWrapper<Nothing>()
	data class NetworkError(val exception: Throwable) : ResultWrapper<Nothing>()
	data class UnknownError(val exception: Throwable) : ResultWrapper<Nothing>()
}


