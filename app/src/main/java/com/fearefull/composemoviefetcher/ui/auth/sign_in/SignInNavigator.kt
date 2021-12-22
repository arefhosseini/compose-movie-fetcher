package com.fearefull.composemoviefetcher.ui.auth.sign_in

import androidx.compose.ui.text.input.TextFieldValue
import com.fearefull.composemoviefetcher.base.ViewEvent
import com.fearefull.composemoviefetcher.base.ViewSideEffect
import com.fearefull.composemoviefetcher.base.ViewState

class SignInNavigator {
    sealed class Event : ViewEvent {
        data class SignInRequested(
            val email: String,
            val password: String
        ) : Event()

        object ToSignUpRequested : Event()

        data class OnEmailChange(
            val value: String
        ) : Event()

        data class OnPasswordChange(
            val value: String
        ) : Event()

        object TogglePasswordVisibility : Event()
    }

    data class State(
        val email: String = "",
        val password: String = "",
        val loading: Boolean = false,
        val passwordVisible: Boolean = false
    ) : ViewState

    sealed class Effect : ViewSideEffect {
        object UserSignedIn : Effect()

        object ErrorUserSignedIn : Effect()

        sealed class Navigation : Effect() {
            object ToSignUp : Navigation()
            object ToMain : Navigation()
        }
    }
}