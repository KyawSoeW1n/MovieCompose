package com.kurio.tetsuya.movie.compose

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException


class AuthInterceptor : Interceptor {
    private val token = BuildConfig.API_KEY
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        request = request.newBuilder()
            .addHeader("Authorization", "Bearer $token")
            .build()

        request = request.newBuilder().build()

        return chain.proceed(request)
    }
}