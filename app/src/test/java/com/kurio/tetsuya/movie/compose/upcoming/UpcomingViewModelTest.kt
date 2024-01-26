package com.kurio.tetsuya.movie.compose.upcoming

import com.kurio.tetsuya.movie.compose.TestDispatcherProvider
import com.kuriotetsuya.domain.upcoming.GetCacheUpcomingListUseCase
import com.kuriotetsuya.domain.fetch_upcoming.FetchUpcomingMovieUseCase
import com.kurio.tetsuya.movie.compose.ui.features.upcoming.viewmodel.UpcomingEvent
import com.kurio.tetsuya.movie.compose.ui.features.upcoming.viewmodel.UpcomingViewModel
import com.kurio.tetsuya.movie.compose.util.CoroutinesDispatchers
import com.kuriotetsuya.domain.model.MovieItemVO
import io.mockk.clearAllMocks
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class UpcomingViewModelTest {
    private lateinit var upcomingViewModel: UpcomingViewModel
    private lateinit var fetchUpcomingMovieUseCase: FetchUpcomingMovieUseCase
    private lateinit var getCacheUpcomingListUseCase: GetCacheUpcomingListUseCase
    private lateinit var updateMovieRepo: UpdateMovieRepo
    private lateinit var coroutinesDispatchers: CoroutinesDispatchers

    @OptIn(ExperimentalCoroutinesApi::class)
    @BeforeEach
    fun setUp() {
        coroutinesDispatchers = TestDispatcherProvider()
        fetchUpcomingMovieUseCase = mockk(relaxed = true)
        getCacheUpcomingListUseCase = mockk(relaxed = true)
        updateMovieRepo = mockk(relaxed = true)
        upcomingViewModel = UpcomingViewModel(
            fetchUpcomingMovieUseCase = fetchUpcomingMovieUseCase,
            getCacheUpcomingListUseCase = getCacheUpcomingListUseCase,
            updateCacheUpcomingMovieRepo = updateMovieRepo,
            coroutinesDispatchers = coroutinesDispatchers
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
        every { getCacheUpcomingListUseCase.getUpcomingList("") } returns flow {
            emit(movieList)
        }

        upcomingViewModel.getCacheUpcomingList().collectLatest {
            assertEquals(movieList, it)
        }
    }

    @Test
    fun getUpcoming_movieList_from_database_by_keyword() = runTest {
        upcomingViewModel.changeUpcomingScreenEvent(UpcomingEvent.SearchEvent("title"))
        assertEquals(upcomingViewModel.upcomingEventState.value, UpcomingEvent.SearchEvent("title"))
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
        every { getCacheUpcomingListUseCase.getUpcomingList("title") } returns flow {
            emit(movieList)
        }

        upcomingViewModel.getCacheUpcomingListByKeyword().collectLatest {
            assertEquals(movieList, it)
        }
    }

    @Test
    fun update_favourite_status() = runTest {
        upcomingViewModel.changeFavouriteStatus(id = 1, flag = false)

        coVerify(exactly = 1) {
            updateMovieRepo.updateCacheUpcomingMovie(id = 1, flag = false)
        }

    }

    @AfterEach
    fun tearDown() {
        clearAllMocks()
    }
}