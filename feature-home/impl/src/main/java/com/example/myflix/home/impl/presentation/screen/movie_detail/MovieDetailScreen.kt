package com.example.myflix.home.impl.presentation.screen.movie_detail

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.myflix.core.presentation.BasicUiState
import com.example.myflix.design_system.presentation.component.FlixButton
import com.example.myflix.home.impl.R

@Composable
fun MovieDetailScreen(
    viewModel: MovieDetailViewModel,
    movieId: String
) {

    val getMovieUiState by viewModel.getMovieUiState.collectAsState(BasicUiState.Idle)
    val storeWatchListUiState by viewModel.storeWatchListUiStateState.collectAsState(BasicUiState.Idle)

    var isUserWatchList: Boolean by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(movieId) {
        viewModel.getMovie(movieId)
    }

    LaunchedEffect(getMovieUiState) {
        when (val state = getMovieUiState) {
            is BasicUiState.Success -> {
                isUserWatchList = state.data.data?.isUserWatchList ?: false
            }
            else -> Unit
        }
    }

    LaunchedEffect(storeWatchListUiState) {
        when (val state = storeWatchListUiState) {
            is BasicUiState.Success -> {
                isUserWatchList = true
            }
            else -> Unit
        }
    }

    Column (
        modifier = Modifier.fillMaxSize()
    ) {
        when (val state = getMovieUiState) {
            is BasicUiState.Success -> {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Movie Title: ${state.data.data?.title.orEmpty()}",
                    color = Color.White
                )
                FlixButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp)
                        .padding(top = 36.dp),
                    enabled = true,
                    isLoading = (storeWatchListUiState is BasicUiState.Loading),
                    buttonText = if (isUserWatchList) R.string.remove_from_watch_list_txt else R.string.add_to_watch_list_txt
                ) {
                    Log.d("TAG", "isUserWatchList: $isUserWatchList")
                    if (!isUserWatchList) {
                        viewModel.storeWatchList(movieId)
                    }
                }
            }
            else -> Unit
        }
    }

}