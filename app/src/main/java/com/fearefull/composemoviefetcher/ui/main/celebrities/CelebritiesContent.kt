package com.fearefull.composemoviefetcher.ui.main.celebrities

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.rememberLazyListState
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
    val listState = rememberLazyListState()
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

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun CelebritiesGrid(
    listState: LazyListState,
    pagingItems: LazyPagingItems<Celebrity>,
    onItemClicked: (item: Celebrity) -> Unit = {}
) {
    LazyVerticalGrid(
        cells = GridCells.Fixed(ThemeConstants.CELEBRITIES_GRID_ITEM_CELL_COUNT),
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