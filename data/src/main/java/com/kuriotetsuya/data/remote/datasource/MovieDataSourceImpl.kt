package com.kuriotetsuya.data.remote.datasource

import com.kurio.tetsuya.movie.compose.core.com.kuriotetsuya.data.remote.datasource.MovieCacheDataSource
import com.kurio.tetsuya.movie.compose.network.ApiService
import com.kuriotetsuya.data.cache.dao.MovieDao
import com.kuriotetsuya.data.cache.entity.CacheMovie
import com.kuriotetsuya.data.network.executeOrThrow
import javax.inject.Inject

class MovieDataSourceImpl @Inject constructor(
    private val apiService: ApiService,
    private val movieDao: MovieDao
) :
    MovieRemoteDataSource, MovieCacheDataSource {
    override fun fetchUpcomingList() = apiService.getUpcoming().executeOrThrow()
    override fun fetchPopularList() = apiService.getPopularList().executeOrThrow()
    override fun fetchRelatedMovie(movieId: Int) =
        apiService.getRelatedMovie(movieId).executeOrThrow()

    override fun fetchMovieDetail(movieId: Int) =
        apiService.getMovieDetail(movieId).executeOrThrow()

    override fun getUpcomingList() = movieDao.getUpcomingList()
    override fun insertUpcomingList(list: List<CacheMovie>) =
        movieDao.insertMovieList(list)

    override fun updateMovie(movieId: Int, flag: Boolean) = movieDao.updateMovie(movieId, flag)

}