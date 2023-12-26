package com.kurio.tetsuya.movie.compose.di

import com.kurio.tetsuya.movie.compose.BuildConfig
import com.kurio.tetsuya.movie.compose.network.ApiService
import com.kurio.tetsuya.movie.compose.network.AuthInterceptor
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun providesRetrofitBuilder(okHttpClient: OkHttpClient): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(provideMoshiBuilder()))
    }

    @Singleton
    @Provides
    fun providesAPI(retrofit: Retrofit.Builder): ApiService {
        return retrofit.build().create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun providerCustomInterceptor(): AuthInterceptor {
        return AuthInterceptor();
    }

    @Singleton
    @Provides
    fun provideMoshiBuilder(): Moshi {
        return Moshi.Builder()
            .build()
    }

    @Provides
    @Singleton
    fun providesLoggingInterceptor(): OkHttpClient {
        val httpInterceptor = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            httpInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        } else {
            httpInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE)
        }

        return OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(providerCustomInterceptor())
            .addInterceptor(httpInterceptor)
            .build()
    }
}