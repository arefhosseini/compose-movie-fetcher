package com.fearefull.composemoviefetcher.ui.main.profile

import com.fearefull.composemoviefetcher.base.ViewEvent
import com.fearefull.composemoviefetcher.base.ViewSideEffect
import com.fearefull.composemoviefetcher.base.ViewState
import com.google.firebase.auth.FirebaseUser

class ProfileNavigator {
    sealed class Event : ViewEvent {
        object SignOutRequested : Event()
    }

    data class State(
        val user: FirebaseUser? = null
    ) : ViewState

    sealed class Effect : ViewSideEffect {
        sealed class Navigation : Effect() {
            object ToAuth : Navigation()
        }
    }
}