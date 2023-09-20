package com.kurio.tetsuya.movie.compose.di

import com.kurio.tetsuya.movie.compose.data.remote.impl.movie_detail.MovieDetailRepo
import com.kurio.tetsuya.movie.compose.data.remote.impl.movie_detail.MovieDetailRepoImpl
import com.kurio.tetsuya.movie.compose.data.remote.impl.related_movie.RelatedMovieRepo
import com.kurio.tetsuya.movie.compose.data.remote.impl.related_movie.RelatedMovieRepoImpl
import com.kurio.tetsuya.movie.compose.domain.app_data.GetAppDataUseCase
import com.kurio.tetsuya.movie.compose.domain.app_data.GetAppDataUseCaseImpl
import com.kurio.tetsuya.movie.compose.domain.cache.locale.ChangeLocaleUseCase
import com.kurio.tetsuya.movie.compose.domain.cache.locale.ChangeLocaleUseCaseImpl
import com.kurio.tetsuya.movie.compose.domain.cache.theme.ChangeThemeStyleUseCase
import com.kurio.tetsuya.movie.compose.domain.cache.theme.ChangeThemeStyleUseCaseImpl
import com.kurio.tetsuya.movie.compose.domain.cache.upcoming.GetCacheUpcomingListUseCase
import com.kurio.tetsuya.movie.compose.domain.cache.upcoming.GetCacheUpcomingListUseCaseImpl
import com.kurio.tetsuya.movie.compose.domain.cache.upcoming.InsertUpcomingListUseCase
import com.kurio.tetsuya.movie.compose.domain.cache.upcoming.InsertUpcomingListUseCaseImpl
import com.kurio.tetsuya.movie.compose.domain.cache.upcoming.UpdateCacheUpcomingMovieUseCase
import com.kurio.tetsuya.movie.compose.domain.cache.upcoming.UpdateCacheUpcomingMovieUseCaseImpl
import com.kurio.tetsuya.movie.compose.domain.remote.fetch_popular.PopularListUseCase
import com.kurio.tetsuya.movie.compose.domain.remote.fetch_popular.PopularListUseCaseImpl
import com.kurio.tetsuya.movie.compose.domain.remote.fetch_upcoming.UpcomingListUseCase
import com.kurio.tetsuya.movie.compose.domain.remote.fetch_upcoming.UpcomingListUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped


@Module
@InstallIn(ViewModelComponent::class)
abstract class ViewModelModule {
    @Binds
    @ViewModelScoped
    abstract fun bindFetchPopularImpl(popularListRepoImpl: PopularListUseCaseImpl): PopularListUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindFetchUpcomingImpl(fetchCarImpl: UpcomingListUseCaseImpl): UpcomingListUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindInsertCarListImpl(insertCarListImpl: InsertUpcomingListUseCaseImpl): InsertUpcomingListUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindGetCarListImpl(insertCarListImpl: GetCacheUpcomingListUseCaseImpl): GetCacheUpcomingListUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindUpdateCachePopularMovieRepoImpl(insertCarListImpl: UpdateCacheUpcomingMovieUseCaseImpl): UpdateCacheUpcomingMovieUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindMovieDetailRepoImpl(movieDetailRepoImpl: MovieDetailRepoImpl): MovieDetailRepo

    @Binds
    @ViewModelScoped
    abstract fun bindRelatedMovieRepoImpl(relatedMovieRepoImpl: RelatedMovieRepoImpl): RelatedMovieRepo


    @Binds
    @ViewModelScoped
    abstract fun bindThemeUseCase(themeUseCaseImpl: GetAppDataUseCaseImpl): GetAppDataUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindChangeThemeStyleUseCase(changeThemeStyleUseCaseImpl: ChangeThemeStyleUseCaseImpl): ChangeThemeStyleUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindChangeLocaleUseCase(changeLocaleUseCaseImpl: ChangeLocaleUseCaseImpl): ChangeLocaleUseCase
}