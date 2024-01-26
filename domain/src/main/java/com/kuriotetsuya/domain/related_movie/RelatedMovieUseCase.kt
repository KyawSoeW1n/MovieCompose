package com.kuriotetsuya.domain.related_movie

import javax.inject.Inject

class RelatedMovieUseCase @Inject constructor(private val relatedMovieRepo: RelatedMovieRepo) {
    suspend fun getRelatedMovieList(movieId: Int) =
        relatedMovieRepo.getRelatedMovie(movieId = movieId)
}