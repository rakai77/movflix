package com.example.myflix.core.data.source.remote

object HttpRoutes {
    const val USER = "/v1/users"
    const val LOGIN_URL = "$USER/login"
    const val REGISTER_URL = "$USER/register"
    const val MOVIES = "/v1/movies"
    const val MOVIE_DETAIL = "/v1/movies/id/{movieId}"
    const val WATCH_LIST = "/v1/watch-list"
}