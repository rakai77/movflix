package com.example.myflix.profile.impl.di

import com.example.myflix.profile.api.ProfileFeature
import com.example.myflix.profile.impl.presentation.ProfileFeatureImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ProfileModule {

    @Binds
    abstract fun bindProfileModule(
        profileFeature: ProfileFeatureImpl
    ) : ProfileFeature
}