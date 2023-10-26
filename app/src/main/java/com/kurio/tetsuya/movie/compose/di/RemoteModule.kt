package com.kurio.tetsuya.movie.compose.di

import com.kurio.tetsuya.movie.compose.data.remote.impl.movie_detail.MovieDetailRepo
import com.kurio.tetsuya.movie.compose.data.remote.impl.movie_detail.MovieDetailRepoImpl
import com.kurio.tetsuya.movie.compose.data.remote.impl.popular.PopularListRepo
import com.kurio.tetsuya.movie.compose.data.remote.impl.popular.PopularListRepoImpl
import com.kurio.tetsuya.movie.compose.data.remote.impl.related_movie.RelatedMovieRepo
import com.kurio.tetsuya.movie.compose.data.remote.impl.related_movie.RelatedMovieRepoImpl
import com.kurio.tetsuya.movie.compose.data.remote.impl.upcoming.UpcomingListRepo
import com.kurio.tetsuya.movie.compose.data.remote.impl.upcoming.UpcomingListRepoImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped


@Module
@InstallIn(ViewModelComponent::class)
abstract class RemoteModule {

    @Binds
    @ViewModelScoped
    abstract fun bindMovieDetailRepoImpl(movieDetailRepoImpl: MovieDetailRepoImpl): MovieDetailRepo

    @Binds
    @ViewModelScoped
    abstract fun bindPopularListRepoImpl(popularListRepoImpl: PopularListRepoImpl): PopularListRepo

    @Binds
    @ViewModelScoped
    abstract fun bindUpcomingListRepoImpl(upcomingListRepo: UpcomingListRepoImpl): UpcomingListRepo

    @Binds
    @ViewModelScoped
    abstract fun bindRelatedMovieRepoImpl(relatedMovieRepoImpl: RelatedMovieRepoImpl): RelatedMovieRepo
}