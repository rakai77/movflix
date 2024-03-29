package com.example.myflix.home.impl.presentation.screen.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.myflix.core.data.source.DataDummy
import com.example.myflix.core.domain.model.MovieItem
import com.example.myflix.core.presentation.BasicUiState
import com.example.myflix.design_system.utils.carouselTransition
import com.example.myflix.home.impl.R

@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    onMovieClick: (String) -> Unit
) {

    var selectedCategoryIndex by rememberSaveable {
        mutableIntStateOf(0)
    }

    val uiState by viewModel.uiState.collectAsState(initial = BasicUiState.Idle)

    LaunchedEffect(selectedCategoryIndex) {
        viewModel.getMovie("12")
    }

    Column(modifier = Modifier.fillMaxSize()) {
        HeaderSection(
            name = "Welcome",
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 40.dp)
                .padding(horizontal = 24.dp)
        )
        MovieCategories(
            modifier = Modifier
                .padding(top = 24.dp)
                .fillMaxWidth(),
            selectedIndex = selectedCategoryIndex,
            onItemSelected = { index ->
                selectedCategoryIndex = index
            }
        )
        when (val state = uiState) {
            is BasicUiState.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(36.dp),
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
            }

            is BasicUiState.Success -> {
                MovieSlider(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = 88.dp),
                    movies = state.data.data.orEmpty()
                ) { movie ->
                    movie.id?.let {
                        onMovieClick.invoke(it.toString())
                    }
                }
            }

            else -> Unit
        }
    }
}

@Composable
fun HeaderSection(
    name: String,
    modifier: Modifier
) {

    val titleSpan = buildAnnotatedString {
        withStyle(style = SpanStyle(fontWeight = FontWeight.SemiBold)) {
            append(stringResource(id = R.string.hello_text))
        }
        append(" ")
        withStyle(style = SpanStyle(fontWeight = FontWeight.Normal)) {
            append(name)
            append(",")
        }
    }

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = titleSpan,
                fontSize = 24.sp,
                color = Color.White,
                style = MaterialTheme.typography.titleSmall
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp),
                text = stringResource(id = R.string.greeting_message),
                color = Color.White,
                style = MaterialTheme.typography.labelMedium
            )
        }
        Image(
            modifier = Modifier
                .padding(start = 16.dp)
                .size(56.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop,
            painter = painterResource(id = R.drawable.person_five),
            contentDescription = null
        )
    }
}

@Composable
fun MovieCategories(
    modifier: Modifier,
    selectedIndex: Int,
    onItemSelected: (Int) -> Unit
) {
    LazyRow(
        modifier = modifier.selectableGroup(),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        contentPadding = PaddingValues(horizontal = 24.dp)
    ) {
        itemsIndexed(DataDummy.categories) { index, category ->
            val isSelected = selectedIndex == index
            Text(
                text = category,
                style = if (isSelected) MaterialTheme.typography.titleSmall else MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.selectable(
                    selected = isSelected,
                    onClick = {
                        onItemSelected(index)
                    }
                )
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MovieSlider(
    modifier: Modifier,
    movies: List<MovieItem>,
    onCardClick: (MovieItem) -> Unit
) {

    val pagerState = rememberPagerState {
        movies.size
    }

    HorizontalPager(
        state = pagerState,
        pageSpacing = 8.dp,
        contentPadding = PaddingValues(24.dp),
        modifier = modifier
    ) { index ->
        movies.getOrNull(index)?.let { movie ->
            Card(
                modifier = Modifier.carouselTransition(index, pagerState),
                shape = RoundedCornerShape(20.dp),
                elevation = CardDefaults.cardElevation(10.dp),
                onClick = { onCardClick(movie) }
            ) {
                AsyncImage(
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop,
                    model = movie.posterUrl,
                    contentDescription = null
                )
            }
        }
    }
}