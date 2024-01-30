package com.kurio.tetsuya.movie.compose.core.com.kuriotetsuya.data.cache.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kuriotetsuya.data.cache.DatabaseConstants
import com.kuriotetsuya.data.cache.UpcomingTableConstants

@Entity(tableName = DatabaseConstants.UPCOMING_TABLE)
data class UpcomingMovie(
    @PrimaryKey
    @ColumnInfo(name = UpcomingTableConstants.id)
    val id: Int,
)
