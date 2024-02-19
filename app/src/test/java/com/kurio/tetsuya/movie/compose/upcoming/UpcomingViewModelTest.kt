package com.kurio.tetsuya.movie.compose.upcoming

import app.cash.turbine.test
import com.kurio.tetsuya.movie.compose.TestDispatcherProvider
import com.kurio.tetsuya.movie.compose.ui.features.upcoming.viewmodel.UpcomingEvent
import com.kurio.tetsuya.movie.compose.ui.features.upcoming.viewmodel.UpcomingViewModel
import com.kurio.tetsuya.movie.compose.util.CoroutinesDispatchers
import com.kuriotetsuya.domain.fetch_upcoming.FetchUpcomingMovieUseCase
import com.kuriotetsuya.domain.get_upcoming.GetUpcomingMovieUseCase
import com.kuriotetsuya.domain.model.MovieItemVO
import com.kuriotetsuya.domain.update_favourite_status.UpdateFavouriteStatusUseCase
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class UpcomingViewModelTest {
    private lateinit var upcomingViewModel: UpcomingViewModel
    private lateinit var fetchUpcomingMovieUseCase: FetchUpcomingMovieUseCase
    private lateinit var getUpcomingMovieUseCase: GetUpcomingMovieUseCase
    private lateinit var updateFavouriteStatusUseCase: UpdateFavouriteStatusUseCase

    private lateinit var coroutinesDispatchers: CoroutinesDispatchers

    @OptIn(ExperimentalCoroutinesApi::class)
    @BeforeEach
    fun setUp() {
        coroutinesDispatchers = TestDispatcherProvider()
        fetchUpcomingMovieUseCase = mockk(relaxed = true)
        getUpcomingMovieUseCase = mockk(relaxed = true)
        updateFavouriteStatusUseCase = mockk(relaxed = true)
        upcomingViewModel = UpcomingViewModel(
            fetchUpcomingMovieUseCase = fetchUpcomingMovieUseCase,
            coroutinesDispatchers = coroutinesDispatchers,
            getUpcomingMovieUseCase = getUpcomingMovieUseCase,
            updateFavouriteStatusUseCase = updateFavouriteStatusUseCase,
        )
    }

    @Test
    fun getUpcoming_movieList_from_database() = runTest {
        upcomingViewModel.changeUpcomingScreenEvent(UpcomingEvent.ResetEvent)

        assertEquals(upcomingViewModel.upcomingEventState.value, UpcomingEvent.ResetEvent)
        //given
        val movieList = persistentListOf(
            MovieItemVO(
                id = 1,
                title = "Testing Title",
                image = "sample image",
                isFavourite = false,
                overview = "Overview",
            )
        )

        coEvery { getUpcomingMovieUseCase.getUpcomingList(keyword = "") } returns flow {
            emit(movieList)
        }
        upcomingViewModel.getCacheUpcomingList("").test {
            assertEquals(movieList, awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }


    @Test
    fun update_favourite_status() = runTest {
        upcomingViewModel.changeFavouriteStatus(id = 1, flag = false)

        coVerify(exactly = 1) {
            updateFavouriteStatusUseCase.updateFavouriteStatus(movieId = 1, flag = false)
        }

    }

    @AfterEach
    fun tearDown() {
        clearAllMocks()
    }
}