package com.kurio.tetsuya.movie.compose.data.cache.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kurio.tetsuya.movie.compose.data.cache.DatabaseConstants

@Entity(tableName = DatabaseConstants.popularTableName)
data class PopularCacheMovie(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,
    val title: String,
    val image: String,
    val description: String,
    @ColumnInfo(name = "isFavourite")
    val isFavourite : Boolean
)