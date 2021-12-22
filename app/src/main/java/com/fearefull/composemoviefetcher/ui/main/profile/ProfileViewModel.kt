package com.fearefull.composemoviefetcher.ui.main.profile

import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fearefull.composemoviefetcher.base.BaseViewModel
import com.fearefull.composemoviefetcher.data.remote.RepositoryAuthenticator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repositoryAuthenticator: RepositoryAuthenticator
) : BaseViewModel<ProfileNavigator.Event, ProfileNavigator.State, ProfileNavigator.Effect>() {

    init {
        viewModelScope.launch { getUser() }
    }

    override fun setInitialState() = ProfileNavigator.State()

    override fun handleEvents(event: ProfileNavigator.Event) {
        when(event) {
            is ProfileNavigator.Event.SignOutRequested -> {
                viewModelScope.launch { signOut() }
            }
        }
    }

    private fun getUser() {
        val user = repositoryAuthenticator.getUser()
        setState {
            copy(user = user)
        }
    }

    private fun signOut() {
        val user = repositoryAuthenticator.signOut()
        user?.let {

        } ?: run {
            setEffect { ProfileNavigator.Effect.Navigation.ToAuth }
        }
    }
}