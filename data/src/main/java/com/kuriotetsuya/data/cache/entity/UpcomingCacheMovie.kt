package com.kuriotetsuya.data.cache.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kuriotetsuya.data.cache.DatabaseConstants

@Entity(tableName = DatabaseConstants.MOVIE_TABLE)
data class CacheMovie(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,
    val title: String,
    val image: String,
    val description: String,
    @ColumnInfo(name = "isFavourite")
    val isFavourite : Boolean
)