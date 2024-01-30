package com.kuriotetsuya.domain.update_favourite_status

interface UpdateFavouriteStatusRepo {
    fun updateFavouriteStatus(movieId: Int, flag: Boolean)
}