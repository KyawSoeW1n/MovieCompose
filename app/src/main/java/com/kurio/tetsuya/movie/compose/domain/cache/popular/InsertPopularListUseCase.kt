package com.kurio.tetsuya.movie.compose.domain.cache.popular

import com.kurio.tetsuya.movie.compose.data.remote.model.movie.MovieItemVO

interface InsertPopularListUseCase {
    fun insertPopularList(carList: List<MovieItemVO>)
}