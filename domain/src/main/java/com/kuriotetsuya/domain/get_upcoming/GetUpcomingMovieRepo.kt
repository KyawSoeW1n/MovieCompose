package com.kuriotetsuya.domain.get_upcoming

import com.kuriotetsuya.domain.model.MovieItemVO
import kotlinx.coroutines.flow.Flow


interface GetUpcomingMovieRepo {
    fun getUpcomingList(keyword :String): Flow<List<MovieItemVO>>
}