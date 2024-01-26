package com.kurio.tetsuya.movie.compose.di

import com.kurio.tetsuya.movie.compose.core.com.kuriotetsuya.data.cache.impl.upcoming.GetUpcomingMovieImpl
import com.kurio.tetsuya.movie.compose.core.com.kuriotetsuya.data.cache.impl.upcoming.UpdateFavouriteStatusImpl
import com.kuriotetsuya.data.remote.impl.movie_detail.MovieDetailRepoImpl
import com.kuriotetsuya.data.remote.impl.popular.PopularListRepoImpl
import com.kuriotetsuya.data.remote.impl.related_movie.RelatedMovieRepoImpl
import com.kuriotetsuya.data.remote.impl.upcoming.UpcomingListMovieRepoImpl
import com.kuriotetsuya.domain.fetch_popular.PopularListUseCase
import com.kuriotetsuya.domain.fetch_popular.PopularListUseCaseImpl
import com.kuriotetsuya.domain.fetch_upcoming.FetchUpcomingMovieRepo
import com.kuriotetsuya.domain.get_upcoming.GetUpcomingMovieRepo
import com.kuriotetsuya.domain.moviedetail.MovieDetailRepo
import com.kuriotetsuya.domain.popular.FetchPopularRepo
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
    abstract fun bindFetchPopularImpl(popularListRepoImpl: PopularListUseCaseImpl): PopularListUseCase


    @Binds
    @ViewModelScoped
    abstract fun bindRelatedMovieUseCaseImpl(relatedMovieRepoImpl: RelatedMovieRepoImpl): RelatedMovieRepo

    @Binds
    @ViewModelScoped
    abstract fun bindUpcomingListRepoImpl(upcomingListRepoImpl: UpcomingListMovieRepoImpl): FetchUpcomingMovieRepo

    @Binds
    @ViewModelScoped
    abstract fun bindPopularListRepoImpl(popularListRepoImpl: PopularListRepoImpl): FetchPopularRepo

    @Binds
    @ViewModelScoped
    abstract fun bindMovieDetailRepoImpl(movieDetailRepoImpl: MovieDetailRepoImpl): MovieDetailRepo

    @Binds
    @ViewModelScoped
    abstract fun bindGetUpcomingMovieImpl(getUpcomingMovieImpl: GetUpcomingMovieImpl): GetUpcomingMovieRepo

    @Binds
    @ViewModelScoped
    abstract fun bindUpdateFavouriteStatusImpl(updateFavouriteStatusImpl: UpdateFavouriteStatusImpl): UpdateFavouriteStatusRepo
//
//    @Binds
//    @ViewModelScoped
//    abstract fun bindThemeUseCase(themeUseCaseImpl: GetThemeUseCaseImpl): GetThemeUseCase
//
//    @Binds
//    @ViewModelScoped
//    abstract fun bindChangeThemeStyleUseCase(changeThemeStyleUseCaseImpl: ChangeThemeStyleUseCaseImpl): ChangeThemeStyleUseCase
//
//    @Binds
//    @ViewModelScoped
//    abstract fun bindChangeDynamicColorUseCaseImpl(changeDynamicColorUseCaseImpl: ChangeDynamicColorUseCaseImpl): ChangeDynamicColorUseCase

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
//    @Binds
//    @ViewModelScoped
//    abstract fun bindChangeLocaleUseCase(changeLocaleUseCaseImpl: ChangeLocaleUseCaseImpl): ChangeLocaleUseCase


}