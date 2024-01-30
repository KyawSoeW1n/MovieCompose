package com.kuriotetsuya.domain.update_favourite_status

import javax.inject.Inject


class UpdateFavouriteStatusUseCase @Inject constructor(private val updateFavouriteStatusRepo: UpdateFavouriteStatusRepo) {
    fun updateFavouriteStatus(movieId: Int, flag: Boolean) =
        updateFavouriteStatusRepo.updateFavouriteStatus(movieId = movieId, flag = flag)
}