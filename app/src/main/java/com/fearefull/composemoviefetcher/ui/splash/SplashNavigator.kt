package com.fearefull.composemoviefetcher.ui.splash

import com.fearefull.composemoviefetcher.base.ViewEvent
import com.fearefull.composemoviefetcher.base.ViewSideEffect
import com.fearefull.composemoviefetcher.base.ViewState
import com.google.firebase.auth.FirebaseUser

class SplashNavigator {
    sealed class Event : ViewEvent {

    }

    data class State(
        val loading: Boolean = false
    ) : ViewState

    sealed class Effect : ViewSideEffect {
        sealed class Navigation : Effect() {
            object ToAuth : Navigation()
            object ToMain : Navigation()
        }
    }
}