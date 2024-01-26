package com.kurio.tetsuya.movie.compose.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class CacheModule {

//    @Binds
//    @ViewModelScoped
//    abstract fun bindUpdateCachePopularMovieRepoImpl(updateCachePopularMovieRepoImpl: UpdateCachePopularMovieRepoImpl): UpdateCachePopularMovieRepo
//
//    @Binds
//    @ViewModelScoped
//    abstract fun bindUpdateCacheUpcomingMovieUseCaseImpl(updateCacheUpcomingMovieUseCaseImpl: UpdateCacheUpcomingMovieRepoImpl): UpdateCacheUpcomingMovieRepo
}