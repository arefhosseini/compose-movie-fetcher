package com.fearefull.composemoviefetcher.ui.main.movies

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.fearefull.composemoviefetcher.data.model.other.Movie
import com.fearefull.composemoviefetcher.ui.components.MoviesGridItem
import com.fearefull.composemoviefetcher.util.constants.ThemeConstants

@Composable
fun MoviesContent(
    movies: LazyPagingItems<Movie>,
    onItemClicked: (item: Movie) -> Unit = {}
) {
    val listState = rememberLazyListState()
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

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun MoviesGrid(
    listState: LazyListState,
    pagingItems: LazyPagingItems<Movie>,
    onItemClicked: (item: Movie) -> Unit = {}
) {
    LazyVerticalGrid(
        cells = GridCells.Fixed(ThemeConstants.MOVIES_GRID_ITEM_CELL_COUNT),
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
                loadState.refresh is
                        LoadState.Loading -> {
                    item { LoadingItem() }
                    item { LoadingItem() }
                }
                loadState.append is
                        LoadState.Loading -> {
                    item { LoadingItem() }
                    item { LoadingItem() }
                }
                loadState.refresh is
                        LoadState.Error -> {}
                loadState.append is
                        LoadState.Error -> {}
            }
        }
    }
}

@Composable
fun LoadingItem() {
    CircularProgressIndicator(
        modifier =
        Modifier.testTag("ProgressBarItem")
            .fillMaxWidth()
            .padding(16.dp)
            .wrapContentWidth(
                Alignment.CenterHorizontally
            )
    )
}