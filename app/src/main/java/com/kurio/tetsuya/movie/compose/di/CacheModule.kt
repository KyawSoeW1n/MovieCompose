package com.kurio.tetsuya.movie.compose.di

import com.kurio.tetsuya.movie.compose.core.com.kuriotetsuya.data.cache.impl.movie.GetPopularMovieImpl
import com.kurio.tetsuya.movie.compose.core.com.kuriotetsuya.data.cache.impl.movie.GetUpcomingMovieImpl
import com.kuriotetsuya.domain.get_popular.GetPopularMovieRepo
import com.kuriotetsuya.domain.get_upcoming.GetUpcomingMovieRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class CacheModule {
    @Binds
    @ViewModelScoped
    abstract fun bindGetUpcomingMovieListImpl(getUpcomingMovieImpl: GetUpcomingMovieImpl): GetUpcomingMovieRepo

    @Binds
    @ViewModelScoped
    abstract fun bindGetPopularMovieImpl(getPopularMovieImpl: GetPopularMovieImpl): GetPopularMovieRepo

}