package com.example.myflix.favorite.impl.di

import com.example.myflix.favorite.api.FavoriteFeature
import com.example.myflix.favorite.impl.presentation.FavoriteFeatureImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class FavoriteModule {

    @Binds
    abstract fun bindFavoriteFeature(
        favoriteFeature: FavoriteFeatureImpl
    ) : FavoriteFeature
}