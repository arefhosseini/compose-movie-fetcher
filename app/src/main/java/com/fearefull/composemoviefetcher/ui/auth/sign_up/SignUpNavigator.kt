package com.fearefull.composemoviefetcher.ui.auth.sign_up

import com.fearefull.composemoviefetcher.base.ViewEvent
import com.fearefull.composemoviefetcher.base.ViewSideEffect
import com.fearefull.composemoviefetcher.base.ViewState
import com.fearefull.composemoviefetcher.ui.auth.sign_in.SignInNavigator

class SignUpNavigator {
    sealed class Event : ViewEvent {
        data class SignUpRequested(
            val email: String,
            val password: String
        ) : Event()

        object ToSignInRequested : Event()

        data class OnEmailChange(
            val value: String
        ) : Event()

        data class OnPasswordChange(
            val value: String
        ) : Event()
    }

    data class State(
        val email: String = "",
        val password: String = "",
        val loading: Boolean = false
    ) : ViewState

    sealed class Effect : ViewSideEffect {
        object UserCreated : Effect()

        object ErrorUserCreated : Effect()

        sealed class Navigation : Effect() {
            object ToSignIn : Navigation()
            object ToMain : Navigation()
        }
    }
}