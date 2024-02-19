package com.kurio.tetsuya.movie.compose.movie_detail

import com.google.common.truth.Truth.assertThat
import com.kuriotetsuya.data.remote.datasource.MovieRemoteDataSourceImpl
import com.kuriotetsuya.data.remote.impl.movie_detail.MovieDetailRepoImpl
import com.kuriotetsuya.domain.ViewState
import com.kuriotetsuya.domain.model.MovieDetailVO
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class MockDetailUseCaseTest {
    lateinit var movieDetailRepoImpl: MovieDetailRepoImpl
    lateinit var movieRemoteDataSourceImpl: MovieRemoteDataSourceImpl

    @BeforeEach
    fun setUp() {
        movieDetailRepoImpl = mockk(relaxed = true)
        movieRemoteDataSourceImpl = mockk(relaxed = true)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `get movie detail successfully`() = runTest {
        val fakeMovieDetailRepo = FakeMovieDetailRepo()

        val expected = fakeMovieDetailRepo.getMovieDetail(movieId = 1)
        advanceUntilIdle()
        assertThat(expected.first()).isEqualTo(
            ViewState.Success(
                MovieDetailVO(
                    id = 1,
                    name = "Hero",
                    overview = "Good Hero",
                    genres = "Comedy,Action",
                    rating = "10"
                )
            )
        )
    }
}