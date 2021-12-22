package com.fearefull.composemoviefetcher.ui.main.profile

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.fearefull.composemoviefetcher.base.LAUNCH_LISTEN_FOR_EFFECTS
import com.fearefull.composemoviefetcher.util.constants.ThemeConstants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

/**
 * Created by Aref Hosseini on ۱۷/۱۱/۲۰۲۱.
 */

@Composable
fun ProfileScreen(
    state: ProfileNavigator.State,
    effectFlow: Flow<ProfileNavigator.Effect>?,
    onEventSent: (event: ProfileNavigator.Event) -> Unit,
    onNavigationSent: (navigationEffect: ProfileNavigator.Effect.Navigation) -> Unit,
) {
    LaunchedEffect(LAUNCH_LISTEN_FOR_EFFECTS) {
        effectFlow?.onEach { effect ->
            when(effect) {
                is ProfileNavigator.Effect.Navigation.ToAuth ->
                    onNavigationSent(effect)
            }
        }?.collect()
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        state.user?.let {
            Text(state.user.email ?: "No Email")
        } ?: run {
            Text("Getting user")
        }
        Spacer(modifier = Modifier.padding(vertical = ThemeConstants.HALF_PADDING.dp))
        Button(
            onClick = { onEventSent(ProfileNavigator.Event.SignOutRequested) },
            contentPadding = PaddingValues(
                horizontal = 20.dp,
                vertical = 12.dp
            )
        ) {
            Text("Sign out")
        }
    }
}