package com.kurio.tetsuya.movie.compose.core

sealed class UseCaseState<out T> {

    object Loading : UseCaseState<Nothing>()

    data class Success<out T>(val successData: T, val message: String? = null) : UseCaseState<T>()

    data class Error(val message: String) : UseCaseState<Nothing>()

    data class Unauthorized(val message: String) : UseCaseState<Nothing>()

    object NetworkError : UseCaseState<Nothing>()

    object ResourceNotFound : UseCaseState<Nothing>()

    object ServerError : UseCaseState<Nothing>()

    object ResetState : UseCaseState<Nothing>()
}
