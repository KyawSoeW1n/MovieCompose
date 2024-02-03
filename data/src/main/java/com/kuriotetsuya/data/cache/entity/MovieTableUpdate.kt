package com.kurio.tetsuya.movie.compose.core.com.kuriotetsuya.data.cache.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MovieTableUpdate(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,
    val title: String,
    val image: String,
    val description: String,
)