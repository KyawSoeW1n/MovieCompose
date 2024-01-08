package com.kurio.tetsuya.movie.compose.data.remote.model.movie

import androidx.compose.runtime.Immutable
import com.kurio.tetsuya.movie.compose.network.response.popular.PopularResponse

@Immutable
class PopularMovieListVO(
    val movieList: MutableList<MovieItemVO>
) {
    data class Builder(
        val movieList: MutableList<MovieItemVO> = mutableListOf(),
    ) {

        fun popularList(response: PopularResponse) = apply {
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

        fun build() = PopularMovieListVO(movieList = movieList)
    }
}