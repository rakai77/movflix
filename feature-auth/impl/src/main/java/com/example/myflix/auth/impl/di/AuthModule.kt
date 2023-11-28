package com.example.myflix.auth.impl.di

import com.example.myflix.auth.api.AuthFeature
import com.example.myflix.auth.impl.AuthFeatureImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AuthModule {

    @Binds
    abstract fun bindAuthFeature(authFeature: AuthFeatureImpl) : AuthFeature
}