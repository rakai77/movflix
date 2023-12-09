package com.example.myflix.home.impl.di

import com.example.myflix.home.api.HomeFeature
import com.example.myflix.home.impl.presentation.HomeFeatureImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class HomeModule {

    @Binds
    abstract fun bindHomeFeature(homeFeature: HomeFeatureImpl) : HomeFeature

}