package com.kurio.tetsuya.movie.compose.domain.remote.related_movie

import com.kurio.tetsuya.movie.compose.data.remote.impl.related_movie.RelatedMovieRepo
import javax.inject.Inject

class RelatedMovieUseCaseImpl @Inject constructor(private val relatedMovieRepo: RelatedMovieRepo) :
    RelatedMovieUseCase {
    override suspend fun getRelatedMovieList(movieId: Int) =
        relatedMovieRepo.getRelatedMovie(movieId = movieId)

}