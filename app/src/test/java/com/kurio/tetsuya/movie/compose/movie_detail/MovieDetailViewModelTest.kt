package com.kurio.tetsuya.movie.compose.movie_detail

import app.cash.turbine.test
import com.kurio.tetsuya.movie.compose.TestDispatcherProvider
import com.kurio.tetsuya.movie.compose.data.remote.model.movie.MovieDetailVO
import com.kurio.tetsuya.movie.compose.data.remote.model.movie.RelatedMovieVO
import com.kurio.tetsuya.movie.compose.domain.remote.moviedetail.MovieDetailUseCaseImpl
import com.kurio.tetsuya.movie.compose.domain.remote.related_movie.RelatedMovieUseCaseImpl
import com.kurio.tetsuya.movie.compose.presentation.ViewState
import com.kurio.tetsuya.movie.compose.ui.features.movedetail.viewmodel.MovieDetailViewModel
import com.kurio.tetsuya.movie.compose.util.CoroutinesDispatchers
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class MovieDetailViewModelTest {

    private lateinit var movieDetailViewModel: MovieDetailViewModel
    private lateinit var movieDetailUseCaseImpl: MovieDetailUseCaseImpl
    private lateinit var relatedMovieUseCaseImpl: RelatedMovieUseCaseImpl
    private lateinit var coroutinesDispatchers: CoroutinesDispatchers

    @OptIn(ExperimentalCoroutinesApi::class)
    @BeforeEach
    fun setUp() {
        coroutinesDispatchers = TestDispatcherProvider()
        movieDetailViewModel = mockk(relaxed = true)
        movieDetailUseCaseImpl = mockk(relaxed = true)
        relatedMovieUseCaseImpl = mockk(relaxed = true)
        movieDetailViewModel = MovieDetailViewModel(
            relatedMovieUseCaseImpl = relatedMovieUseCaseImpl,
            movieDetailUseCaseImpl = movieDetailUseCaseImpl,
            coroutinesDispatchers = coroutinesDispatchers
        )
    }

    @Test
    fun get_movie_detail_by_id_success() = runTest {
        val movieDetailVO = ViewState.Success(
            MovieDetailVO(
                name = "Movie Detail Test",
                overview = "Detail Overview",
                genres = "Drama",
                rating = "8.5",
            )
        )

        coEvery { movieDetailUseCaseImpl.getMovieDetail(movieId = 1) } returns flow {
            emit(movieDetailVO)
        }

        assertEquals(ViewState.Loading, movieDetailViewModel.movieDetailStateFlow.value)
        movieDetailViewModel.changeMovieId(movieId = 1)

        coVerify(exactly = 1) {
            movieDetailUseCaseImpl.getMovieDetail(movieId = 1)
        }

        movieDetailViewModel.movieDetailStateFlow.test {
            assertEquals(movieDetailVO, awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun get_related_movie_by_id_success() = runTest {
        val relatedMovieVO = ViewState.Success(
            listOf(
                RelatedMovieVO(
                    id = 1,
                    name = "Related Movie 1",
                    rating = "8.4",
                    image = "Test Image 1"
                ),
                RelatedMovieVO(
                    id = 2,
                    name = "Related Movie 2",
                    rating = "3.4",
                    image = "Test Image 2"
                )
            )
        )

        coEvery { relatedMovieUseCaseImpl.getRelatedMovieList(movieId = 1) } returns flow {
            emit(relatedMovieVO)
        }

        assertEquals(ViewState.Loading, movieDetailViewModel.relatedMovieStateFlow.value)
        movieDetailViewModel.changeMovieId(movieId = 1)

        coVerify(exactly = 1) {
            relatedMovieUseCaseImpl.getRelatedMovieList(movieId = 1)
        }

        movieDetailViewModel.relatedMovieStateFlow.test {
            assertEquals(relatedMovieVO, awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun get_movie_detail_by_id_error() = runTest {
        val movieDetailVO = ViewState.Error("Error")
        coEvery { movieDetailUseCaseImpl.getMovieDetail(movieId = 1) } returns flow {
            emit(movieDetailVO)
        }

        assertEquals(ViewState.Loading, movieDetailViewModel.movieDetailStateFlow.value)
        movieDetailViewModel.changeMovieId(movieId = 1)

        coVerify {
            movieDetailUseCaseImpl.getMovieDetail(movieId = 1)
        }

        movieDetailViewModel.movieDetailStateFlow.test {
            assertEquals(movieDetailVO, awaitItem())
            cancelAndIgnoreRemainingEvents()
        }

    }

    @Test
    fun get_related_movie_by_id_error() = runTest {
        val movieDetailVO = ViewState.Error("Error")
        coEvery { relatedMovieUseCaseImpl.getRelatedMovieList(movieId = 1) } returns flow {
            emit(movieDetailVO)
        }

        assertEquals(ViewState.Loading, movieDetailViewModel.movieDetailStateFlow.value)
        movieDetailViewModel.changeMovieId(movieId = 1)

        coVerify {
            relatedMovieUseCaseImpl.getRelatedMovieList(movieId = 1)
        }

        movieDetailViewModel.relatedMovieStateFlow.test {
            assertEquals(movieDetailVO, awaitItem())
            cancelAndIgnoreRemainingEvents()
        }

    }

    @AfterEach
    fun tearDown() {
        clearAllMocks()
    }

}