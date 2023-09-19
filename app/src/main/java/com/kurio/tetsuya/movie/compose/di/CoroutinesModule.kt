package com.kurio.tetsuya.movie.compose.di

import com.kurio.tetsuya.movie.compose.util.CoroutinesDispatchers
import com.kurio.tetsuya.movie.compose.util.CoroutinesDispatchersImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface CoroutinesModule {
    @Binds
    @Singleton
    fun bindCoroutinesDispatchers(
        impl: CoroutinesDispatchersImpl
    ): CoroutinesDispatchers
}