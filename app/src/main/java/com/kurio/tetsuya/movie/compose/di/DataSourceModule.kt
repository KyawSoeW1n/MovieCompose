package com.kurio.tetsuya.movie.compose.di

import com.kurio.tetsuya.movie.compose.data.remote.datasource.MovieDataSource
import com.kurio.tetsuya.movie.compose.data.remote.datasource.MovieDataSourceImpl
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
    fun bindMovieDataSourceImpl(movieDataSourceImpl: MovieDataSourceImpl): MovieDataSource
}