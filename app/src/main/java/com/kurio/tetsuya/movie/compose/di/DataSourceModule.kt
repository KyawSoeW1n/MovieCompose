package com.kurio.tetsuya.movie.compose.di

import com.kurio.tetsuya.movie.compose.core.com.kuriotetsuya.data.cache.datasource.app_configuration.AppConfigurationDataSource
import com.kurio.tetsuya.movie.compose.core.com.kuriotetsuya.data.cache.datasource.app_configuration.AppConfigurationDataSourceImpl
import com.kurio.tetsuya.movie.compose.core.com.kuriotetsuya.data.cache.datasource.movie.MovieCacheDataSource
import com.kurio.tetsuya.movie.compose.core.com.kuriotetsuya.data.cache.datasource.movie.MovieCacheDataSourceImpl
import com.kuriotetsuya.data.remote.datasource.MovieRemoteDataSource
import com.kuriotetsuya.data.remote.datasource.MovieRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped


@Module
@InstallIn(ViewModelComponent::class)
interface DataSourceModule {
    @Binds
    @ViewModelScoped
    fun bindMovieDataSourceImpl(movieRemoteDataSourceImpl: MovieRemoteDataSourceImpl): MovieRemoteDataSource

    @Binds
    @ViewModelScoped
    fun bindMovieCacheDataSourceImpl(movieCacheDataSourceImpl: MovieCacheDataSourceImpl): MovieCacheDataSource

    @Binds
    @ViewModelScoped
    fun bindAppConfigurationDataSourceImpl(appConfigurationDataSourceImpl: AppConfigurationDataSourceImpl): AppConfigurationDataSource
}