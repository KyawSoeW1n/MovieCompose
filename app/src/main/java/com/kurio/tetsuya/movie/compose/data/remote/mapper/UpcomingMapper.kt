package com.kurio.tetsuya.movie.compose.data.remote.mapper

import com.kurio.tetsuya.movie.compose.data.remote.model.movie.UpcomingMovieListVO
import com.kurio.tetsuya.movie.compose.network.response.upcoming.UpcomingResponse

class UpcomingMapper {
    fun mapFromResponse(
        response: UpcomingResponse,
    ): UpcomingMovieListVO {
        return UpcomingMovieListVO.Builder()
            .upcomingList(response = response)
            .build()
    }
}