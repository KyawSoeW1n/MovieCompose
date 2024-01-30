package com.kuriotetsuya.domain.fetch_popular

import javax.inject.Inject


class FetchPopularMovieUseCase @Inject constructor(private val fetchPopularMovieRepo: FetchPopularMovieRepo) :
    FetchPopularMovieRepo {
    override fun fetchPopularList() = fetchPopularMovieRepo.fetchPopularList()

}