package com.kurio.tetsuya.movie.compose.core.com.kuriotetsuya.data.cache.impl.upcoming

import com.kuriotetsuya.data.remote.datasource.MovieDataSourceImpl
import com.kuriotetsuya.domain.get_upcoming.GetUpcomingMovieRepo
import com.kuriotetsuya.domain.model.MovieItemVO
import com.kuriotetsuya.domain.update_favourite_status.UpdateFavouriteStatusRepo
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.mapLatest
import javax.inject.Inject

class UpdateFavouriteStatusImpl @Inject constructor(
    private val movieDataSourceImpl: MovieDataSourceImpl,
) : UpdateFavouriteStatusRepo {


    override fun updateFavouriteStatus(movieId: Int, flag: Boolean) =
        movieDataSourceImpl.updateMovie(movieId, flag)

}