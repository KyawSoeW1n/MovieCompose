package com.kurio.tetsuya.movie.compose.presentation.com.example.domain

sealed class ViewState<out T> {

    object Loading : ViewState<Nothing>()

    data class Success<out T>(val successData: T, val message: String? = null) : ViewState<T>()

    data class Error(val message: String) : ViewState<Nothing>()

    data class Unauthorized(val message: String) : ViewState<Nothing>()

    object NetworkError : ViewState<Nothing>()

    object ResourceNotFound : ViewState<Nothing>()

    object ServerError : ViewState<Nothing>()
}
