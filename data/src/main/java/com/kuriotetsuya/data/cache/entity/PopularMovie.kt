package com.kurio.tetsuya.movie.compose.core.com.kuriotetsuya.data.cache.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kuriotetsuya.data.cache.DatabaseConstants
import com.kuriotetsuya.data.cache.PopularTableConstants

@Entity(tableName = DatabaseConstants.POPULAR_TABLE)
data class PopularMovie(
    @PrimaryKey
    @ColumnInfo(name = PopularTableConstants.id)
    val id: Int,
)