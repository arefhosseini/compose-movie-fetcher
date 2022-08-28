package com.fearefull.composemoviefetcher.ui.main.celebrities

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
import com.fearefull.composemoviefetcher.data.model.other.Celebrity
import com.fearefull.composemoviefetcher.ui.components.CelebritiesGridItem
import com.fearefull.composemoviefetcher.ui.components.LoadingItem
import com.fearefull.composemoviefetcher.util.constants.ThemeConstants

@Composable
fun CelebritiesContent(
    celebrities: LazyPagingItems<Celebrity>,
    onItemClicked: (item: Celebrity) -> Unit = {}
) {
    val listState = rememberLazyGridState()
    when(celebrities.loadState.refresh) {
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
            val error = celebrities.loadState.refresh as LoadState.Error
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
            CelebritiesGrid(listState, celebrities, onItemClicked)
        }
    }
}

@Composable
private fun CelebritiesGrid(
    listState: LazyGridState,
    pagingItems: LazyPagingItems<Celebrity>,
    onItemClicked: (item: Celebrity) -> Unit = {}
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(ThemeConstants.CELEBRITIES_GRID_ITEM_CELL_COUNT),
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
                CelebritiesGridItem(
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