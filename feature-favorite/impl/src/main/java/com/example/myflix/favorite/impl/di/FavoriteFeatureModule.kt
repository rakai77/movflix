package com.example.myflix.favorite.impl.di

import com.example.myflix.favorite.impl.presentation.FavoriteFeatureImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object FavoriteFeatureModule {

    @Provides
    fun provideFavoriteFeature() = FavoriteFeatureImpl()
}