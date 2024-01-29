package com.kuriotetsuya.data.remote.mapper

import com.kurio.tetsuya.movie.compose.network.response.popular.PopularResponse
import com.kuriotetsuya.data.convertNetworkString
import com.kuriotetsuya.domain.model.MovieItemVO
import com.kuriotetsuya.domain.model.PopularMovieListVO

class PopularMapper {
    fun mapFromResponse(
        response: PopularResponse,
    ): PopularMovieListVO {
        val list = response.results.map {
            MovieItemVO(
                id = it.id,
                image = it.posterPath.convertNetworkString(),
                title = it.title,
                isFavourite = false,
                overview = it.overview
            )
        }.toMutableList()
        return PopularMovieListVO(list)
    }
}