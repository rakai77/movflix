package com.example.myflix.core.di

import com.example.myflix.core.data.interactor.AuthInteractor
import com.example.myflix.core.data.interactor.MovieInteractor
import com.example.myflix.core.domain.repository.AuthRepository
import com.example.myflix.core.domain.repository.MovieRepository
import com.example.myflix.core.domain.usecase.AuthUseCase
import com.example.myflix.core.domain.usecase.MovieUseCase
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

    @Provides
    fun provideMovieUseCase(movieRepository: MovieRepository) : MovieUseCase = MovieInteractor(Dispatchers.IO, movieRepository)
}