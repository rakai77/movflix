package com.example.myflix.core.di

import com.example.myflix.core.data.interactor.AuthInteractor
import com.example.myflix.core.domain.repository.AuthRepository
import com.example.myflix.core.domain.usecase.AuthUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(ViewModelComponent::class)
class UseCaseModule {

    @Provides
    fun provideAuthUseCase(authRepository: AuthRepository) : AuthUseCase = AuthInteractor(Dispatchers.IO, authRepository)
}