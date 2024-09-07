package com.test.downloadmanager.core

sealed class LoadingState<T>(
    val data: T? = null, val error: String? = null
) {
    class Idle<T>() : LoadingState<T>()
    class Loading<T>() : LoadingState<T>()
    class Failure<T>(error: String?) : LoadingState<T>(error = error, data = null)
    class Success<T>(data: T) : LoadingState<T>(data, error = null)
}
