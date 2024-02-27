package com.example.myflix.core.di

import com.example.myflix.core.data.source.local.AppDataStore
import com.example.myflix.core.data.source.remote.HttpClientFactory
import com.example.myflix.core.data.source.remote.service.AuthService
import com.example.myflix.core.data.source.remote.service.MovieService
import com.example.myflix.core.data.source.remote.service.impl.AuthServiceImpl
import com.example.myflix.core.data.source.remote.service.impl.MovieServiceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun provideHttpClient(appDataStore: AppDataStore): HttpClient = HttpClientFactory(appDataStore).create()

    @Provides
    fun provideAuthService(httpClient: HttpClient): AuthService = AuthServiceImpl(httpClient)

    @Provides
    fun provideMovieService(httpClient: HttpClient): MovieService = MovieServiceImpl(httpClient)
}