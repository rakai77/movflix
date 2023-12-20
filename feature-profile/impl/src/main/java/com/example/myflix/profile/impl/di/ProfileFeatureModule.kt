package com.example.myflix.profile.impl.di

import com.example.myflix.profile.impl.presentation.ProfileFeatureImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ProfileFeatureModule {

    @Provides
    fun provideProfileFeature() = ProfileFeatureImpl()
}