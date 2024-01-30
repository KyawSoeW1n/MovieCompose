package com.kurio.tetsuya.movie.compose.di

import com.kuriotetsuya.data.remote.mapper.PopularMapper
import com.kuriotetsuya.data.remote.mapper.UpcomingMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object MapperModule {
    @Provides
    @ViewModelScoped
    fun provideUpcomingMapper() = UpcomingMapper()

    @Provides
    @ViewModelScoped
    fun providePopularMapper() = PopularMapper()
}