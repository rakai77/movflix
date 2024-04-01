package com.example.myflix.home.impl.presentation.screen.movie_detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.myflix.core.domain.model.MovieItem
import com.example.myflix.core.presentation.BasicUiState
import com.example.myflix.design_system.presentation.theme.Gold
import com.example.myflix.design_system.presentation.theme.Gray
import com.example.myflix.home.impl.R
import com.example.myflix.home.impl.data.randomPersonImage
import com.example.myflix.home.impl.utils.getYearByDate
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.haze
import dev.chrisbanes.haze.hazeChild
import dev.chrisbanes.haze.materials.ExperimentalHazeMaterialsApi
import dev.chrisbanes.haze.materials.HazeMaterials
import kotlin.random.Random

@Composable
fun MovieDetailScreen(
    viewModel: MovieDetailViewModel,
    movieId: String,
    onBackClick: () -> Unit
) {

    val getMovieUiState by viewModel.getMovieUiState.collectAsState(BasicUiState.Idle)
    val hazeState = remember { HazeState() }
    var isUserWatchList: Boolean by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(movieId) {
        viewModel.getMovie(movieId)
    }

    LaunchedEffect(viewModel.isUserWatchList) {
        isUserWatchList = viewModel.isUserWatchList
    }

    LaunchedEffect(getMovieUiState) {
        when (val state = getMovieUiState) {
            is BasicUiState.Success -> {
                viewModel.isUserWatchList = state.data.data?.isUserWatchList ?: false
            }
            else -> Unit
        }
    }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        when (val state = getMovieUiState) {
            is BasicUiState.Success -> {
                MovieHeaderSection(
                    modifier = Modifier.fillMaxWidth(),
                    hazeState = hazeState,
                    movie = state.data.data,
                    isMovieWatchlist = isUserWatchList,
                    onWatchlistClick = {
                        if (it) {
                            viewModel.removeWatchList(movieId)
                        } else {
                            viewModel.storeWatchList(movieId)
                        }
                    },
                    onBackClick = onBackClick
                )
                CastSection(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 24.dp),
                    movie = state.data.data
                )
                OverviewSection(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 24.dp)
                        .padding(horizontal = 24.dp),
                    movie = state.data.data
                )
            }
            else -> Unit
        }
    }
}

@OptIn(ExperimentalHazeMaterialsApi::class)
@Composable
fun MovieHeaderSection(
    modifier: Modifier,
    hazeState: HazeState,
    movie: MovieItem?,
    isMovieWatchlist: Boolean,
    onWatchlistClick: (Boolean) -> Unit,
    onBackClick: () -> Unit,
) {

    Box(
        modifier = modifier
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .haze(hazeState),
            contentScale = ContentScale.Crop,
            model = movie?.posterUrl,
            contentDescription = null
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color.Transparent, Color.Black),
                        startY = 0f,
                        endY = 600f
                    )
                )
        )
        IconButton(
            modifier = Modifier
                .padding(24.dp)
                .hazeChild(
                    hazeState,
                    style = HazeMaterials.thin(),
                    shape = RoundedCornerShape(4.dp)
                )
                .size(36.dp)
                .align(Alignment.TopStart),
            onClick = { onBackClick() }
        ) {
            Icon(
                modifier = Modifier,
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                tint = Color.White,
                contentDescription = null
            )
        }
        IconButton(
            modifier = Modifier
                .padding(24.dp)
                .hazeChild(
                    hazeState,
                    style = HazeMaterials.thin(),
                    shape = RoundedCornerShape(5.dp)
                )
                .size(36.dp)
                .align(Alignment.TopEnd),
            onClick = { onWatchlistClick(isMovieWatchlist) }
        ) {
            Icon(
                modifier = Modifier,
                imageVector = Icons.Filled.Favorite,
                tint = if (isMovieWatchlist) Color.Red else Color.White,
                contentDescription = null
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                text = movie?.title.orEmpty(),
                style = MaterialTheme.typography.titleSmall.copy(
                    textAlign = TextAlign.Center
                ),
                color = MaterialTheme.colorScheme.onBackground
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                val categories = movie?.category.orEmpty().takeLast(2)
                
                Text(
                    text = movie?.releaseDate?.getYearByDate().orEmpty(),
                    style = MaterialTheme.typography.labelMedium,
                    color = Color.Gray
                )
                VerticalDivider(
                    modifier = Modifier
                        .height(18.dp)
                        .padding(horizontal = 8.dp),
                    thickness = 1.dp
                )
                categories.forEachIndexed { index, category ->
                    Text(
                        text = "$category ${if (index != categories.size - 1) ", " else ""}",
                        style = MaterialTheme.typography.labelMedium,
                        color = Color.Gray
                    )
                }
                VerticalDivider(
                    modifier = Modifier
                        .height(18.dp)
                        .padding(horizontal = 8.dp),
                    thickness = 1.dp
                )
                Text(
                    text = movie?.director.orEmpty(),
                    style = MaterialTheme.typography.labelMedium,
                    color = Color.Gray
                )
            }
            Rating(
                modifier = Modifier
                    .padding(top = 8.dp)
                    .height(24.dp),
                rating = movie?.rating ?: 0
            )
        }
    }
}

@Composable
fun CastSection(
    modifier: Modifier,
    movie: MovieItem?
) {
    Column(modifier = modifier) {
        Text(
            modifier = Modifier.padding(start = 24.dp),
            text = stringResource(id = R.string.cast_txt),
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onBackground
        )
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(24.dp),
            contentPadding = PaddingValues(horizontal = 24.dp)
        ) {
            items(movie?.cast.orEmpty()) {
                Box(
                    modifier = Modifier
                        .width(100.dp)
                        .height(160.dp)
                        .clip(RoundedCornerShape(16.dp))
                ) {
                    AsyncImage(
                        model = "https://i.pravatar.cc/150?u=${it}",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop,
                        contentDescription = null
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                brush = Brush.verticalGradient(
                                    colors = listOf(Color.Transparent, Color.Black),
                                    startY = 0f,
                                    endY = 400f
                                )
                            )
                    )
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp)
                            .padding(bottom = 16.dp)
                            .align(Alignment.BottomCenter),
                        text = it,
                        style = MaterialTheme.typography.labelSmall.copy(
                            textAlign = TextAlign.Center
                        ),
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
            }
        }
    }
}

@Composable
fun OverviewSection(
    modifier: Modifier,
    movie: MovieItem?
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = stringResource(id = R.string.overview_txt),
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onBackground
        )
        Text(
            modifier = Modifier.padding(top = 12.dp),
            text = movie?.overview.orEmpty(),
            style = MaterialTheme.typography.labelSmall,
            color = Gray
        )
    }
}

@Composable
fun Rating(
    modifier: Modifier,
    rating: Int
) {
    Row(
        modifier = modifier
    ) {
        repeat(5) {
            Icon(
                modifier = Modifier.fillMaxHeight(),
                imageVector = Icons.Filled.Star,
                tint = if (it <= rating) Gold else Gray,
                contentDescription = null
            )
        }
    }
}