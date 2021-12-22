package com.fearefull.composemoviefetcher.ui.auth.sign_in

import androidx.lifecycle.viewModelScope
import com.fearefull.composemoviefetcher.base.BaseViewModel
import com.fearefull.composemoviefetcher.data.remote.RepositoryAuthenticator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val repositoryAuthenticator: RepositoryAuthenticator
) : BaseViewModel<SignInNavigator.Event, SignInNavigator.State, SignInNavigator.Effect>() {

    override fun setInitialState() = SignInNavigator.State()

    override fun handleEvents(event: SignInNavigator.Event) {
        when(event) {
            is SignInNavigator.Event.SignInRequested -> {
                // TODO: Handle request for sign in user
                viewModelScope.launch { signIn(event.email, event.password) }
            }
            is SignInNavigator.Event.ToSignUpRequested -> {
                setEffect { SignInNavigator.Effect.Navigation.ToSignUp }
            }
            is SignInNavigator.Event.OnEmailChange -> {
                setState {
                    copy(email = event.value)
                }
            }
            is SignInNavigator.Event.OnPasswordChange -> {
                setState {
                    copy(password = event.value)
                }
            }
            is SignInNavigator.Event.TogglePasswordVisibility -> {
                setState {
                    copy(passwordVisible = !passwordVisible)
                }
            }
        }
    }

    private suspend fun signIn(email: String, password: String) {
        setState { copy(loading = true) }
        val user = repositoryAuthenticator.signInWithEmailPassword(email, password)
        setState { copy(loading = false) }
        user?.let {
//            setEffect { SignInNavigator.Effect.UserSignedIn }
            // TODO: After 1 sec:
            setEffect { SignInNavigator.Effect.Navigation.ToMain }
        } ?: run {
           setEffect { SignInNavigator.Effect.ErrorUserSignedIn }
        }
    }
}