package com.kurio.tetsuya.movie.compose.di

import com.kurio.tetsuya.movie.compose.domain.app_data.GetThemeUseCase
import com.kurio.tetsuya.movie.compose.domain.app_data.GetThemeUseCaseImpl
import com.kurio.tetsuya.movie.compose.domain.cache.locale.ChangeLocaleUseCase
import com.kurio.tetsuya.movie.compose.domain.cache.locale.ChangeLocaleUseCaseImpl
import com.kurio.tetsuya.movie.compose.domain.cache.popular.GetCachePopularListUseCase
import com.kurio.tetsuya.movie.compose.domain.cache.popular.GetCachePopularListUseCaseImpl
import com.kurio.tetsuya.movie.compose.domain.cache.popular.GetCachePopularMovieDetailUseCase
import com.kurio.tetsuya.movie.compose.domain.cache.popular.GetCachePopularMovieDetailUseCaseImpl
import com.kurio.tetsuya.movie.compose.domain.cache.theme.ChangeDynamicColorUseCase
import com.kurio.tetsuya.movie.compose.domain.cache.theme.ChangeDynamicColorUseCaseImpl
import com.kurio.tetsuya.movie.compose.domain.cache.theme.ChangeThemeStyleUseCase
import com.kurio.tetsuya.movie.compose.domain.cache.theme.ChangeThemeStyleUseCaseImpl
import com.kurio.tetsuya.movie.compose.domain.cache.upcoming.GetCacheUpcomingListUseCase
import com.kurio.tetsuya.movie.compose.domain.cache.upcoming.GetCacheUpcomingListUseCaseImpl
import com.kurio.tetsuya.movie.compose.domain.cache.upcoming.GetCacheUpcomingMovieDetailUseCase
import com.kurio.tetsuya.movie.compose.domain.cache.upcoming.GetCacheUpcomingMovieDetailUseCaseImpl
import com.kurio.tetsuya.movie.compose.domain.cache.upcoming.InsertUpcomingListUseCase
import com.kurio.tetsuya.movie.compose.domain.cache.upcoming.InsertUpcomingListUseCaseImpl
import com.kurio.tetsuya.movie.compose.domain.remote.fetch_popular.PopularListUseCase
import com.kurio.tetsuya.movie.compose.domain.remote.fetch_popular.PopularListUseCaseImpl
import com.kurio.tetsuya.movie.compose.domain.remote.fetch_upcoming.UpcomingListUseCase
import com.kurio.tetsuya.movie.compose.domain.remote.fetch_upcoming.UpcomingListUseCaseImpl
import com.kurio.tetsuya.movie.compose.domain.remote.moviedetail.MovieDetailUseCase
import com.kurio.tetsuya.movie.compose.domain.remote.moviedetail.MovieDetailUseCaseImpl
import com.kurio.tetsuya.movie.compose.domain.remote.related_movie.RelatedMovieUseCase
import com.kurio.tetsuya.movie.compose.domain.remote.related_movie.RelatedMovieUseCaseImpl
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
    abstract fun bindFetchUpcomingImpl(upcomingListUseCaseImpl: UpcomingListUseCaseImpl): UpcomingListUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindInsertCarListImpl(insertUpcomingListUseCaseImpl: InsertUpcomingListUseCaseImpl): InsertUpcomingListUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindGetCarListImpl(getCacheUpcomingListUseCaseImpl: GetCacheUpcomingListUseCaseImpl): GetCacheUpcomingListUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindMovieDetailUseCaseImpl(popularListRepoImpl: MovieDetailUseCaseImpl): MovieDetailUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindThemeUseCase(themeUseCaseImpl: GetThemeUseCaseImpl): GetThemeUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindChangeThemeStyleUseCase(changeThemeStyleUseCaseImpl: ChangeThemeStyleUseCaseImpl): ChangeThemeStyleUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindChangeDynamicColorUseCaseImpl(changeDynamicColorUseCaseImpl: ChangeDynamicColorUseCaseImpl): ChangeDynamicColorUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindGetCachePopularListUseCaseImpl(getCachePopularListUseCaseImpl: GetCachePopularListUseCaseImpl): GetCachePopularListUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindGetCachePopularMovieDetailUseCase(getCachePopularMovieDetailUseCaseImpl: GetCachePopularMovieDetailUseCaseImpl): GetCachePopularMovieDetailUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindGetCacheUpcomingMovieDetailUseCaseImpl(getCacheUpcomingMovieDetailUseCaseImpl: GetCacheUpcomingMovieDetailUseCaseImpl): GetCacheUpcomingMovieDetailUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindChangeLocaleUseCase(changeLocaleUseCaseImpl: ChangeLocaleUseCaseImpl): ChangeLocaleUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindRelatedMovieUseCaseImpl(relatedMovieUseCaseImpl: RelatedMovieUseCaseImpl): RelatedMovieUseCase
}