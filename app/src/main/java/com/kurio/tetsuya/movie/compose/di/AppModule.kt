package com.kurio.tetsuya.movie.compose.di

import com.kurio.tetsuya.movie.compose.core.com.kuriotetsuya.data.cache.impl.app_configuration.AppConfigurationImpl
import com.kuriotetsuya.domain.theme.AppConfigurationRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface AppModule {
    @Binds
    @Singleton
    fun bindChangeThemeUseCaseImpl(appConfigurationImpl: AppConfigurationImpl): AppConfigurationRepo
}