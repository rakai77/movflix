package com.example.myflix.home.impl.di

import com.example.myflix.home.api.HomeFeature
import com.example.myflix.home.impl.presentation.HomeFeatureImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject

@Module
@InstallIn(SingletonComponent::class)
object HomeFeatureModule {

    @Provides
    fun provideHomeFeature() = HomeFeatureImpl()
}