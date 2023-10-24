package com.kurio.tetsuya.movie.compose.upcoming

import com.kurio.tetsuya.movie.compose.TestDispatcherProvider
import com.kurio.tetsuya.movie.compose.data.remote.model.movie.MovieItemVO
import com.kurio.tetsuya.movie.compose.domain.cache.upcoming.GetCacheUpcomingListUseCaseImpl
import com.kurio.tetsuya.movie.compose.domain.cache.upcoming.UpdateCacheUpcomingMovieUseCaseImpl
import com.kurio.tetsuya.movie.compose.domain.remote.fetch_upcoming.UpcomingListUseCaseImpl
import com.kurio.tetsuya.movie.compose.ui.features.upcoming.viewmodel.UpcomingViewModel
import com.kurio.tetsuya.movie.compose.util.CoroutinesDispatchers
import io.mockk.clearAllMocks
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
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
    private lateinit var upcomingListUseCaseImpl: UpcomingListUseCaseImpl
    private lateinit var getCacheUpcomingListUseCaseImpl: GetCacheUpcomingListUseCaseImpl
    private lateinit var updateCacheUpcomingMovieUseCaseImpl: UpdateCacheUpcomingMovieUseCaseImpl
    private lateinit var coroutinesDispatchers: CoroutinesDispatchers
    @OptIn(ExperimentalCoroutinesApi::class)
    @BeforeEach
    fun setUp() {
        coroutinesDispatchers = TestDispatcherProvider()
        upcomingListUseCaseImpl = mockk(relaxed = true)
        getCacheUpcomingListUseCaseImpl = mockk(relaxed = true)
        updateCacheUpcomingMovieUseCaseImpl = mockk(relaxed = true)
        upcomingViewModel = UpcomingViewModel(
            upcomingListUseCaseImpl = upcomingListUseCaseImpl,
            getCacheUpcomingListUseCaseImpl = getCacheUpcomingListUseCaseImpl,
            updateCacheUpcomingMovieRepoImpl = updateCacheUpcomingMovieUseCaseImpl,
            coroutinesDispatchers = coroutinesDispatchers
        )
    }

    @Test
    fun getUpcoming_movieList_from_database() = runTest {
        //given
        val movieList = listOf(
            MovieItemVO(
                id = 1,
                title = "Testing Title",
                image = "sample image",
                isFavourite = false,
                overview = "Overview",
            )
        )
        every { getCacheUpcomingListUseCaseImpl.getUpcomingList() } returns flow {
            emit(movieList)
        }

        upcomingViewModel.getCacheUpcomingList().collectLatest {
            assertEquals(movieList, it)
        }
    }

    @Test
    fun update_favourite_status() = runTest {
        upcomingViewModel.changeFavouriteStatus(id = 1, flag = false)

        coVerify(exactly = 1) {
            updateCacheUpcomingMovieUseCaseImpl.updateCacheUpcomingMovie(id = 1, flag = false)
        }

    }

    @AfterEach
    fun tearDown() {
        clearAllMocks()
    }
}