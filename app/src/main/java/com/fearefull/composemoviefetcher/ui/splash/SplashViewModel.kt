package com.fearefull.composemoviefetcher.ui.splash

import androidx.lifecycle.viewModelScope
import com.fearefull.composemoviefetcher.base.BaseViewModel
import com.fearefull.composemoviefetcher.data.remote.RepositoryAuthenticator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val repositoryAuthenticator: RepositoryAuthenticator
) : BaseViewModel<SplashNavigator.Event, SplashNavigator.State, SplashNavigator.Effect>() {

    init {
        viewModelScope.launch { getUser() }
    }

    override fun setInitialState() = SplashNavigator.State(loading = true)

    override fun handleEvents(event: SplashNavigator.Event) {

    }

    private fun getUser() {
        val user = repositoryAuthenticator.getUser()
        user?.let {
            setEffect { SplashNavigator.Effect.Navigation.ToMain }
        } ?: run {
            setEffect { SplashNavigator.Effect.Navigation.ToAuth }
        }
    }
}