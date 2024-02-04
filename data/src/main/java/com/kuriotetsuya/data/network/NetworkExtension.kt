package com.kuriotetsuya.data.network

import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonSyntaxException
import com.kurio.tetsuya.movie.compose.network.NetworkException
import com.kuriotetsuya.domain.ViewState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeout
import retrofit2.Call
import retrofit2.HttpException
import java.net.ConnectException
import java.net.HttpURLConnection
import java.net.ProtocolException
import java.net.UnknownHostException

val gson = Gson()
suspend fun <T> safeApiCall(
    dispatcher: CoroutineDispatcher = Dispatchers.IO,
    apiCall: suspend () -> T?
): ViewState<T?> {

    return withContext(dispatcher) {
        try {
            // throws TimeoutCancellationException
            withTimeout(10000) {
                ViewState.Success(apiCall())
            }
        } catch (throwable: Throwable) {
            throwable.printStackTrace()
            when (throwable) {
                is UnknownHostException, is ConnectException -> ViewState.NetworkError
                is NetworkException -> {
                    var message = throwable.errorBody
                    if (message.isNullOrEmpty()) {
                        message = "Unauthorized"
                    }
                    when (throwable.errorCode) {
                        HttpURLConnection.HTTP_BAD_REQUEST -> ViewState.Error("Bad Request")
                        HttpURLConnection.HTTP_INTERNAL_ERROR -> ViewState.ServerError
                        HttpURLConnection.HTTP_UNAUTHORIZED, HttpURLConnection.HTTP_FORBIDDEN -> ViewState.Unauthorized(
                            message
                        )
                        HttpURLConnection.HTTP_NOT_FOUND -> ViewState.ResourceNotFound
                        else -> ViewState.NetworkError
                    }
                }
                is HttpException -> {
                    when (throwable.code()) {
                        HttpURLConnection.HTTP_BAD_REQUEST -> ViewState.Error("Bad Request")
                        HttpURLConnection.HTTP_INTERNAL_ERROR -> ViewState.ServerError
                        HttpURLConnection.HTTP_NOT_FOUND -> ViewState.ResourceNotFound
                        else -> ViewState.NetworkError
                    }
                }
                is IllegalArgumentException -> ViewState.Error("Format Exception")
                is ProtocolException -> ViewState.ServerError
                is JsonSyntaxException -> ViewState.Error("Format Exception")
                else -> ViewState.NetworkError
            }

        }
    }
}


fun <T> Call<T>.executeOrThrow(): T {
    val response = this.execute()
    println("------ ${response.code()}")
    if (response.isSuccessful.not()) {
        if (response.code() == 401) {
            val data = response.errorBody()?.string()
            val convertedObject = gson.fromJson(data, JsonObject::class.java)
            val message = convertedObject.get("message").asString
            throw NetworkException(response.code(), errorBody = message)
        } else {
            throw NetworkException(response.code())
        }
    }
    return response.body() ?: throw NetworkException()
}