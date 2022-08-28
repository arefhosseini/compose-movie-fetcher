package com.fearefull.composemoviefetcher.ui.main.celebrity_details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.fearefull.composemoviefetcher.base.BaseViewModel
import com.fearefull.composemoviefetcher.data.remote.RepositoryCelebrity
import com.fearefull.composemoviefetcher.data.remote.RepositoryMovie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CelebrityDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val repositoryCelebrity: RepositoryCelebrity
) : BaseViewModel<CelebrityDetailsNavigator.Event, CelebrityDetailsNavigator.State, CelebrityDetailsNavigator.Effect>() {
    private val celebrityId: Long = savedStateHandle.get("celebrityId")!!

    init {
        viewModelScope.launch {
            fetchCelebrityDetails()
        }
    }

    override fun setInitialState() = CelebrityDetailsNavigator.State(loading = true)

    override fun handleEvents(event: CelebrityDetailsNavigator.Event) {
        when(event) {

            else -> {}
        }
    }

    private suspend fun fetchCelebrityDetails() {
        val celebrity = repositoryCelebrity.fetchCelebrityDetails(celebrityId)
        setState {
            copy(celebrity = celebrity, loading = false)
        }
        setEffect { CelebrityDetailsNavigator.Effect.DataWasLoaded }
    }
}