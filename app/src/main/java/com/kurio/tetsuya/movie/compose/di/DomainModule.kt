package com.kurio.tetsuya.movie.compose.di

import com.kurio.tetsuya.movie.compose.core.com.kuriotetsuya.data.cache.impl.movie.GetMovieDetailImpl
import com.kurio.tetsuya.movie.compose.core.com.kuriotetsuya.data.cache.impl.movie.UpdateFavouriteStatusImpl
import com.kuriotetsuya.data.remote.impl.movie_detail.MovieDetailRepoImpl
import com.kuriotetsuya.data.remote.impl.popular.PopularMovieListRepoImpl
import com.kuriotetsuya.data.remote.impl.related_movie.RelatedMovieRepoImpl
import com.kuriotetsuya.data.remote.impl.upcoming.UpcomingMovieListRepoImpl
import com.kuriotetsuya.domain.fetch_popular.FetchPopularMovieRepo
import com.kuriotetsuya.domain.fetch_upcoming.FetchUpcomingMovieRepo
import com.kuriotetsuya.domain.moviedetail.GetCacheMovieDetailRepo
import com.kuriotetsuya.domain.moviedetail.MovieDetailRepo
import com.kuriotetsuya.domain.related_movie.RelatedMovieRepo
import com.kuriotetsuya.domain.update_favourite_status.UpdateFavouriteStatusRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped


@Module
@InstallIn(ViewModelComponent::class)
abstract class DomainModule {

    @Binds
    @ViewModelScoped
    abstract fun bindRelatedMovieUseCaseImpl(relatedMovieRepoImpl: RelatedMovieRepoImpl): RelatedMovieRepo

    @Binds
    @ViewModelScoped
    abstract fun bindUpcomingListRepoImpl(upcomingListRepoImpl: UpcomingMovieListRepoImpl): FetchUpcomingMovieRepo

    @Binds
    @ViewModelScoped
    abstract fun bindPopularListRepoImpl(popularMovieListRepoImpl: PopularMovieListRepoImpl): FetchPopularMovieRepo

    @Binds
    @ViewModelScoped
    abstract fun bindMovieDetailRepoImpl(movieDetailRepoImpl: MovieDetailRepoImpl): MovieDetailRepo

    @Binds
    @ViewModelScoped
    abstract fun bindUpdateFavouriteStatusImpl(updateFavouriteStatusImpl: UpdateFavouriteStatusImpl): UpdateFavouriteStatusRepo

    @Binds
    @ViewModelScoped
    abstract fun bindGetMovieDetailImpl(getMovieDetailImpl: GetMovieDetailImpl): GetCacheMovieDetailRepo

//    @Binds
//    @ViewModelScoped
//    abstract fun bindGetCachePopularListUseCaseImpl(getCachePopularListUseCaseImpl: GetCachePopularListUseCaseImpl): GetCachePopularListUseCase
//
//    @Binds
//    @ViewModelScoped
//    abstract fun bindGetCachePopularMovieDetailUseCase(getCachePopularMovieDetailUseCaseImpl: GetCachePopularMovieDetailUseCaseImpl): GetCachePopularMovieDetailUseCase
//
//    @Binds
//    @ViewModelScoped
//    abstract fun bindGetCacheUpcomingMovieDetailUseCaseImpl(getCacheUpcomingMovieDetailUseCaseImpl: GetCacheUpcomingMovieDetailUseCaseImpl): GetCacheUpcomingMovieDetailUseCase
//


}