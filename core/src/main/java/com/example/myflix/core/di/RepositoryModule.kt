package com.example.myflix.core.di

import com.example.myflix.core.data.repository.AuthRepositoryImpl
import com.example.myflix.core.data.repository.MovieRepositoryImpl
import com.example.myflix.core.data.repository.UserRepositoryImpl
import com.example.myflix.core.data.source.remote.service.AuthService
import com.example.myflix.core.data.source.remote.service.MovieService
import com.example.myflix.core.data.source.remote.service.UserService
import com.example.myflix.core.domain.repository.AuthRepository
import com.example.myflix.core.domain.repository.MovieRepository
import com.example.myflix.core.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class RepositoryModule {

    @Provides
    fun provideAuthRepository(authService: AuthService): AuthRepository = AuthRepositoryImpl(authService)

    @Provides
    fun provideMovieRepository(movieService: MovieService) : MovieRepository = MovieRepositoryImpl(movieService)

    @Provides
    fun provideUserRepository(userService: UserService) : UserRepository = UserRepositoryImpl(userService)

}