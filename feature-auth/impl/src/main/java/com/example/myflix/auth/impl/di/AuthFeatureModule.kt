package com.example.myflix.auth.impl.di

import com.example.myflix.auth.impl.AuthFeatureImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthFeatureModule {

    @Provides
    @Singleton
    fun provideAuthFeature() = AuthFeatureImpl()
}