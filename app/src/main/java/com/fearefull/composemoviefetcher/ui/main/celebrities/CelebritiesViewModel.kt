package com.fearefull.composemoviefetcher.ui.main.celebrities

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.fearefull.composemoviefetcher.base.BaseViewModel
import com.fearefull.composemoviefetcher.data.model.other.Celebrity
import com.fearefull.composemoviefetcher.data.remote.RepositoryCelebrity
import com.fearefull.composemoviefetcher.util.constants.AppConstants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class CelebritiesViewModel @Inject constructor(
    private val repositoryCelebrity: RepositoryCelebrity
) : BaseViewModel<CelebritiesNavigator.Event, CelebritiesNavigator.State, CelebritiesNavigator.Effect>() {

    init {
        val celebrities: Flow<PagingData<Celebrity>> = Pager(
            config = PagingConfig(AppConstants.CELEBRITIES_PAGING_PAGE_SIZE)
        ) {
            CelebritiesPagingSource(
                repositoryCelebrity = repositoryCelebrity,
                onDataWasLoaded = {setEffect { CelebritiesNavigator.Effect.DataWasLoaded }}
            )
        }
            .flow.cachedIn(viewModelScope)
        setState {
            copy(celebrities = celebrities)
        }
    }

    override fun setInitialState() = CelebritiesNavigator.State()

    override fun handleEvents(event: CelebritiesNavigator.Event) {
        when(event) {
            is CelebritiesNavigator.Event.CelebritySelection -> {
                setEffect { CelebritiesNavigator.Effect.Navigation.ToCelebrityDetails(event.celebrity) }
            }
        }
    }
}