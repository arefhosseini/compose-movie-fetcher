package com.fearefull.composemoviefetcher.ui.auth.sign_up

import androidx.lifecycle.viewModelScope
import com.fearefull.composemoviefetcher.base.BaseViewModel
import com.fearefull.composemoviefetcher.data.remote.RepositoryAuthenticator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val repositoryAuthenticator: RepositoryAuthenticator
) : BaseViewModel<SignUpNavigator.Event, SignUpNavigator.State, SignUpNavigator.Effect>() {

    override fun setInitialState() = SignUpNavigator.State()

    override fun handleEvents(event: SignUpNavigator.Event) {
        when(event) {
            is SignUpNavigator.Event.SignUpRequested -> {
                // TODO: Handle request for create user
                viewModelScope.launch { createUser(event.email, event.password) }
            }
            is SignUpNavigator.Event.ToSignInRequested -> {
                setEffect { SignUpNavigator.Effect.Navigation.ToSignIn }
            }
            is SignUpNavigator.Event.OnEmailChange -> {
                setState {
                    copy(email = event.value)
                }
            }
            is SignUpNavigator.Event.OnPasswordChange -> {
                setState {
                    copy(password = event.value)
                }
            }
            is SignUpNavigator.Event.TogglePasswordVisibility -> {
                setState {
                    copy(passwordVisible = !passwordVisible)
                }
            }
        }
    }

    private suspend fun createUser(email: String, password: String) {
        setState { copy(loading = true) }
        val user = repositoryAuthenticator.signUpWithEmailPassword(email, password)
        setState { copy(loading = false) }
        user?.let {
//            setEffect { SignUpNavigator.Effect.UserCreated }
            // TODO: After 1 sec :
            setEffect { SignUpNavigator.Effect.Navigation.ToMain }
        } ?: run {
            setEffect { SignUpNavigator.Effect.ErrorUserCreated }
        }
    }
}