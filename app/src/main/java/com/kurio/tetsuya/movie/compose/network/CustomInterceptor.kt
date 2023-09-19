package com.kurio.tetsuya.movie.compose.network

import com.kurio.tetsuya.movie.compose.core.AppConstants
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException


class CustomInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        request = request.newBuilder()
            .addHeader("Authorization", "Bearer ${AppConstants.token}")
            .build()

        request = request.newBuilder().build()

        return chain.proceed(request)
    }
}