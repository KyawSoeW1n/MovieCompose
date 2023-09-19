package com.kurio.tetsuya.movie.compose.domain.remote.related_movie

import com.kurio.tetsuya.movie.compose.data.remote.impl.related_movie.RelatedMovieRepoImpl
import javax.inject.Inject

class RelatedMovieUseCaseImpl @Inject constructor(private val relatedMovieRepoImpl: RelatedMovieRepoImpl) :
    RelatedMovieUseCase {
    override suspend fun getRelatedMovieList(movieId: Int) =
        relatedMovieRepoImpl.getRelatedMovie(movieId = movieId)

}