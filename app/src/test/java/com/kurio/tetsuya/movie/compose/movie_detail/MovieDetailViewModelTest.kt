package com.kurio.tetsuya.movie.compose.movie_detail

import app.cash.turbine.test
import com.kurio.tetsuya.movie.compose.TestDispatcherProvider
import com.kurio.tetsuya.movie.compose.core.UseCaseState
import com.kurio.tetsuya.movie.compose.data.remote.impl.movie_detail.MovieDetailRepo
import com.kurio.tetsuya.movie.compose.data.remote.model.movie.MovieDetailVO
import com.kurio.tetsuya.movie.compose.data.remote.model.movie.RelatedMovieVO
import com.kurio.tetsuya.movie.compose.domain.remote.moviedetail.MovieDetailUseCase
import com.kurio.tetsuya.movie.compose.domain.remote.related_movie.RelatedMovieUseCase
import com.kurio.tetsuya.movie.compose.network.response.movie_detail.Genre
import com.kurio.tetsuya.movie.compose.network.response.movie_detail.MovieDetailResponse
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
import java.text.DecimalFormat

class MovieDetailViewModelTest {

    private lateinit var movieDetailViewModel: MovieDetailViewModel
    private lateinit var movieDetailUseCase: MovieDetailUseCase
    private lateinit var movieDetailRepo: MovieDetailRepo
    private lateinit var relatedMovieUseCase: RelatedMovieUseCase
    private lateinit var coroutinesDispatchers: CoroutinesDispatchers

    @OptIn(ExperimentalCoroutinesApi::class)
    @BeforeEach
    fun setUp() {
        coroutinesDispatchers = TestDispatcherProvider()
        movieDetailRepo = mockk(relaxed = true)
        movieDetailViewModel = mockk(relaxed = true)
        movieDetailUseCase = mockk(relaxed = true)
        relatedMovieUseCase = mockk(relaxed = true)
        movieDetailViewModel = MovieDetailViewModel(
            relatedMovieUseCase = relatedMovieUseCase,
            movieDetailUseCase = movieDetailUseCase,
            coroutinesDispatchers = coroutinesDispatchers
        )
    }

    @Test
    fun get_movie_detail_by_id_success() = runTest {

        val response = UseCaseState.Success(
            MovieDetailResponse(
                title = "Movie Detail Test",
                overview = "Movie Detail Overview",
                genres = listOf(
                    Genre(
                        id = 1,
                        name = "Comedy",
                    )
                ),
                voteAverage = 7.0,
            )
        )
        val decimalFormat = DecimalFormat("#.00")
        val movieDetailVO =
            ViewState.Success(
                response.successData.let { data ->
                    MovieDetailVO(
                        name = data.title ?: "",
                        overview = data.overview ?: "",
                        genres = data.genres?.joinToString { it.name } ?: "",
                        rating = decimalFormat.format(data.voteAverage)
                    )
                }
            )


        coEvery { movieDetailUseCase.getMovieDetail(movieId = 1) } returns flow {
            emit(movieDetailVO)
        }

        assertEquals(ViewState.Loading, movieDetailViewModel.movieDetailStateFlow.value)
        movieDetailViewModel.changeMovieId(movieId = 1)

        coVerify(exactly = 1) {
            movieDetailUseCase.getMovieDetail(movieId = 1)
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

        coEvery { relatedMovieUseCase.getRelatedMovieList(movieId = 1) } returns flow {
            emit(relatedMovieVO)
        }

        assertEquals(ViewState.Loading, movieDetailViewModel.relatedMovieStateFlow.value)
        movieDetailViewModel.changeMovieId(movieId = 1)

        coVerify(exactly = 1) {
            relatedMovieUseCase.getRelatedMovieList(movieId = 1)
        }
        coVerify(exactly = 1) {
            movieDetailUseCase.getMovieDetail(movieId = 1)
        }

        coVerify {
            movieDetailRepo.getMovieDetail(movieId = 1)
        }


        movieDetailViewModel.relatedMovieStateFlow.test {
            assertEquals(relatedMovieVO, awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun get_movie_detail_by_id_error() = runTest {
        val movieDetailVO = ViewState.Error("Error")
        coEvery { movieDetailUseCase.getMovieDetail(movieId = 1) } returns flow {
            emit(movieDetailVO)
        }

        assertEquals(ViewState.Loading, movieDetailViewModel.movieDetailStateFlow.value)
        movieDetailViewModel.changeMovieId(movieId = 1)

        coVerify {
            movieDetailUseCase.getMovieDetail(movieId = 1)
        }

        movieDetailViewModel.movieDetailStateFlow.test {
            assertEquals(movieDetailVO, awaitItem())
            cancelAndIgnoreRemainingEvents()
        }

    }

    @Test
    fun get_related_movie_by_id_error() = runTest {
        val movieDetailVO = ViewState.Error("Error")
        coEvery { relatedMovieUseCase.getRelatedMovieList(movieId = 1) } returns flow {
            emit(movieDetailVO)
        }

        assertEquals(ViewState.Loading, movieDetailViewModel.movieDetailStateFlow.value)
        movieDetailViewModel.changeMovieId(movieId = 1)

        coVerify {
            relatedMovieUseCase.getRelatedMovieList(movieId = 1)
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