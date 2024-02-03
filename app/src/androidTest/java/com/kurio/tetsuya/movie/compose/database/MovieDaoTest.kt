package com.kurio.tetsuya.movie.compose.database

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.kurio.tetsuya.movie.compose.core.com.kuriotetsuya.data.cache.entity.MovieTableUpdate
import com.kuriotetsuya.data.cache.MovieDatabase
import com.kuriotetsuya.data.cache.dao.MovieDao
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
class MovieDaoTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val hiltAndroidRule = HiltAndroidRule(this)

    @Inject
    lateinit var movieDataBase: MovieDatabase
    private lateinit var movieDao: MovieDao

    @Before
    fun setUp() {
        hiltAndroidRule.inject()
        movieDao = movieDataBase.provideMovieDao()
    }

    @Test
    fun insertPopular_returnsSinglePopular() = runTest {
        val movie = MovieTableUpdate(
            id = 1,
            title = "Title",
            image = "https://images.unsplash.com/photo-1597429926308-ffc8cd6f55fd?crop=entropy&cs=tinysrgb&fit=crop&fm=jpg&h=100&ixid=MnwxfDB8MXxyYW5kb218MHx8fHx8fHx8MTY5MDQyNTc3OA&ixlib=rb-4.0.3&q=80&utm_campaign=api-credit&utm_medium=referral&utm_source=unsplash_source&w=100",
            description = "Description",
        )
        movieDao.insertMovieList(mutableListOf(movie))
    }


    @After
    fun closeDatabase() {
        movieDataBase.close()
    }
}