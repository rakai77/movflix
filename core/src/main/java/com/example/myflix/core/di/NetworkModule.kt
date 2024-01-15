package com.example.myflix.core.di

import com.example.myflix.core.data.source.local.AppDataStore
import com.example.myflix.core.data.source.remote.HttpClientFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    val hashMap = hashMapOf<String, Any>(
        "pageNumber" to 0,
        "keyword" to "Sepatu"
    )

    @Provides
    fun provideHttpClient(appDataStore: AppDataStore): HttpClient = HttpClientFactory(appDataStore).create()

}