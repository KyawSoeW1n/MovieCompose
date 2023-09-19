package com.kurio.tetsuya.movie.compose.data.remote.mapper

import com.kurio.tetsuya.movie.compose.data.remote.model.movie.PopularMovieListVO
import com.kurio.tetsuya.movie.compose.network.response.popular.PopularResponse

class PopularMapper {
    fun mapFromResponse(
        response: PopularResponse,
    ): PopularMovieListVO {
        return PopularMovieListVO.Builder()
            .popularList(response = response)
            .build()
    }
}