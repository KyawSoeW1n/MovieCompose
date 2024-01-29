package com.kuriotetsuya.data.remote.mapper

import com.kurio.tetsuya.movie.compose.network.response.upcoming.UpcomingResponse
import com.kuriotetsuya.data.convertNetworkString
import com.kuriotetsuya.domain.model.MovieItemVO
import com.kuriotetsuya.domain.model.UpcomingMovieListVO

class UpcomingMapper {
    fun mapFromResponse(
        response: UpcomingResponse,
    ): UpcomingMovieListVO {
        val list = response.results.map {
            MovieItemVO(
                id = it.id,
                image = it.posterPath.convertNetworkString(),
                title = it.title,
                isFavourite = false,
                overview = it.overview
            )
        }.toMutableList()
        return UpcomingMovieListVO(list)
    }
}