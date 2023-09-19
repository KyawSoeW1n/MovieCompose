package com.kurio.tetsuya.movie.compose.network

import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonSyntaxException
import com.kurio.tetsuya.movie.compose.core.UseCaseState
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
): UseCaseState<T?> {

    return withContext(dispatcher) {
        try {
            // throws TimeoutCancellationException
            withTimeout(10000) {
                UseCaseState.Success(apiCall())
            }
        } catch (throwable: Throwable) {
            throwable.printStackTrace()
            when (throwable) {
                is UnknownHostException, is ConnectException -> UseCaseState.NetworkError
                is NetworkException -> {
                    var message = throwable.errorBody
                    if (message.isNullOrEmpty()) {
                        message = "Unauthorized"
                    }
                    when (throwable.errorCode) {
                        HttpURLConnection.HTTP_BAD_REQUEST -> UseCaseState.Error("Bad Request")
                        HttpURLConnection.HTTP_INTERNAL_ERROR -> UseCaseState.ServerError
                        HttpURLConnection.HTTP_UNAUTHORIZED, HttpURLConnection.HTTP_FORBIDDEN -> UseCaseState.Unauthorized(
                            message
                        )
                        HttpURLConnection.HTTP_NOT_FOUND -> UseCaseState.ResourceNotFound
                        else -> UseCaseState.NetworkError
                    }
                }
                is HttpException -> {
                    when (throwable.code()) {
                        HttpURLConnection.HTTP_BAD_REQUEST -> UseCaseState.Error("Bad Request")
                        HttpURLConnection.HTTP_INTERNAL_ERROR -> UseCaseState.ServerError
                        HttpURLConnection.HTTP_NOT_FOUND -> UseCaseState.ResourceNotFound
                        else -> UseCaseState.NetworkError
                    }
                }
                is IllegalArgumentException -> UseCaseState.Error("Format Exception")
                is ProtocolException -> UseCaseState.ServerError
                is JsonSyntaxException -> UseCaseState.Error("Format Exception")
                else -> UseCaseState.NetworkError
            }

        }
    }
}


fun <T> Call<T>.executeOrThrow(): T {
    val response = this.execute()
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