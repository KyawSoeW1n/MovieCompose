package com.kurio.tetsuya.movie.compose.core.com.kuriotetsuya.data.cache.datasource.movie

import com.kurio.tetsuya.movie.compose.core.com.kuriotetsuya.data.cache.entity.PopularMovie
import com.kurio.tetsuya.movie.compose.core.com.kuriotetsuya.data.cache.entity.UpcomingMovie
import com.kuriotetsuya.data.cache.dao.MovieDao
import com.kuriotetsuya.data.cache.entity.MovieTable
import javax.inject.Inject

class MovieCacheDataSourceImpl @Inject constructor(
    private val movieDao: MovieDao
) : MovieCacheDataSource {

    override fun getUpcomingMovieList() = movieDao.getUpcomingMovieList()

    override fun getPopularMovieList() = movieDao.getPopularMovieList()

    override fun insertMovieList(list: List<MovieTable>) =
        movieDao.insertMovieList(list)

    override fun insertPopularMovieList(list: List<PopularMovie>) =
        movieDao.insertPopularMovie(list)


    override fun insertUpcomingMovieList(list: List<UpcomingMovie>) =
        movieDao.insertUpcomingMovie(list)

    override fun updateMovie(movieId: Int, flag: Boolean) = movieDao.updateMovie(movieId, flag)

    override fun getMovieDetail(movieId: Int) = movieDao.getMovieDetail(movieId)
}