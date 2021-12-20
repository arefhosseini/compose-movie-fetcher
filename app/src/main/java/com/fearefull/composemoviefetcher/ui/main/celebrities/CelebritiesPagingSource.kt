package com.fearefull.composemoviefetcher.ui.main.celebrities

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.fearefull.composemoviefetcher.data.model.other.Celebrity
import com.fearefull.composemoviefetcher.data.model.remote.toCelebrities
import com.fearefull.composemoviefetcher.data.remote.RepositoryCelebrity

class CelebritiesPagingSource (
    private val repositoryCelebrity: RepositoryCelebrity,
    private val onDataWasLoaded: () -> Unit
) : PagingSource<Int, Celebrity>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Celebrity> {
        return try {
            val page = params.key ?: 1
            val responseList = repositoryCelebrity.fetchCelebrities(page)
            val loadResult = LoadResult.Page(
                data = responseList.toCelebrities(),
                prevKey = if (page == 1) null else page.minus(1),
                nextKey = responseList.page.plus(1)
            )
            onDataWasLoaded()
            loadResult
        } catch (ex: Exception) {
            LoadResult.Error(ex)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Celebrity>): Int = 1
}