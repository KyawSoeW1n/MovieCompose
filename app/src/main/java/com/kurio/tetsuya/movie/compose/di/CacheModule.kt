package com.kurio.tetsuya.movie.compose.di

import com.kurio.tetsuya.movie.compose.data.cache.impl.popular.UpdateCachePopularMovieRepo
import com.kurio.tetsuya.movie.compose.data.cache.impl.popular.UpdateCachePopularMovieRepoImpl
import com.kurio.tetsuya.movie.compose.data.cache.impl.upcoming.UpdateCacheUpcomingMovieRepo
import com.kurio.tetsuya.movie.compose.data.cache.impl.upcoming.UpdateCacheUpcomingMovieRepoImpl
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
    abstract fun bindUpdateCachePopularMovieRepoImpl(updateCachePopularMovieRepoImpl: UpdateCachePopularMovieRepoImpl): UpdateCachePopularMovieRepo

    @Binds
    @ViewModelScoped
    abstract fun bindUpdateCacheUpcomingMovieUseCaseImpl(updateCacheUpcomingMovieUseCaseImpl: UpdateCacheUpcomingMovieRepoImpl): UpdateCacheUpcomingMovieRepo
}