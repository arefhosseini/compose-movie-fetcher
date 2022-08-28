package com.fearefull.composemoviefetcher.ui.main.movies

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.fearefull.composemoviefetcher.data.model.other.Movie
import com.fearefull.composemoviefetcher.ui.components.LoadingItem
import com.fearefull.composemoviefetcher.ui.components.MoviesGridItem
import com.fearefull.composemoviefetcher.util.constants.ThemeConstants

@Composable
fun MoviesContent(
    movies: LazyPagingItems<Movie>,
    onItemClicked: (item: Movie) -> Unit = {}
) {
    val listState = rememberLazyGridState()
    when (movies.loadState.refresh) {
        is LoadState.Loading -> {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Loading...")
            }
        }
        is LoadState.Error -> {
            val error = movies.loadState.refresh as LoadState.Error
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(error.error.message.orEmpty())
            }
        }
        else -> {
            MoviesGrid(listState, movies, onItemClicked)
        }
    }
}

@Composable
private fun MoviesGrid(
    listState: LazyGridState,
    pagingItems: LazyPagingItems<Movie>,
    onItemClicked: (item: Movie) -> Unit = {}
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(ThemeConstants.MOVIES_GRID_ITEM_CELL_COUNT),
        contentPadding = PaddingValues(
            start = ThemeConstants.HALF_PADDING.dp,
            end = ThemeConstants.HALF_PADDING.dp,
            bottom = ThemeConstants.HALF_PADDING.dp
        ),
        horizontalArrangement = Arrangement.spacedBy(ThemeConstants.HALF_PADDING.dp, Alignment.CenterHorizontally),
        state = listState,
    ) {
        items(pagingItems.itemCount) { index ->
            pagingItems[index]?.let {
                MoviesGridItem(
                    item = it,
                    onItemClicked = onItemClicked
                )
            }
        }
        pagingItems.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    item { LoadingItem() }
                }
                loadState.append is LoadState.Loading -> {
                    item { LoadingItem() }
                }
                loadState.refresh is LoadState.Error -> {}
                loadState.append is LoadState.Error -> {}
            }
        }
    }
}
