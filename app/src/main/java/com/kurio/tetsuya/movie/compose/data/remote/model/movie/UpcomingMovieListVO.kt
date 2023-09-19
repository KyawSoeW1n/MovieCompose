package com.kurio.tetsuya.movie.compose.data.remote.model.movie

import com.kurio.tetsuya.movie.compose.network.response.upcoming.UpcomingResponse

class UpcomingMovieListVO constructor(
    val movieList: MutableList<MovieItemVO>
) {
    data class Builder(
        val movieList: MutableList<MovieItemVO> = mutableListOf(),
    ) {

        fun upcomingList(response: UpcomingResponse) = apply {
            movieList.addAll(response.results.map {
                MovieItemVO(
                    id = it.id,
                    image = it.posterPath,
                    title = it.title,
                    isFavourite = false,
                    overview = it.overview
                )
            })
        }

        fun build() = UpcomingMovieListVO(movieList = movieList)
    }
}