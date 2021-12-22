package com.fearefull.composemoviefetcher.ui.auth.sign_in

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.fearefull.composemoviefetcher.base.LAUNCH_LISTEN_FOR_EFFECTS
import com.fearefull.composemoviefetcher.util.constants.ThemeConstants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

@Composable
fun SignInScreen(
    state: SignInNavigator.State,
    effectFlow: Flow<SignInNavigator.Effect>?,
    onEventSent: (event: SignInNavigator.Event) -> Unit,
    onNavigationSent: (navigationEffect: SignInNavigator.Effect.Navigation) -> Unit,
) {
    LaunchedEffect(LAUNCH_LISTEN_FOR_EFFECTS) {
        effectFlow?.onEach { effect ->
            when(effect) {
                is SignInNavigator.Effect.Navigation.ToMain ->
                    onNavigationSent(effect)
                is SignInNavigator.Effect.Navigation.ToSignUp ->
                    onNavigationSent(effect)
                is SignInNavigator.Effect.UserSignedIn -> {

                }
                is SignInNavigator.Effect.ErrorUserSignedIn -> {

                }
            }
        }?.collect()
    }
    Column(
        modifier = Modifier.fillMaxWidth().fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    )
    {
        TextField(
            value = state.email,
            onValueChange = { onEventSent(SignInNavigator.Event.OnEmailChange(it)) },
            label = { Text(text = "Email") },
            modifier = Modifier
                .padding(all = 16.dp)
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.None,
                keyboardType = KeyboardType.Email,
            ),
            singleLine = true,
            leadingIcon = {
                Icon(Icons.Filled.Email, contentDescription = null)
            }
        )
        TextField(
            value = state.password,
            onValueChange = { onEventSent(SignInNavigator.Event.OnPasswordChange(it)) },
            label = { Text(text = "Password") },
            modifier = Modifier
                .padding(all = 16.dp)
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
            ),
            visualTransformation = if (state.passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            singleLine = true,
            leadingIcon = {
                Icon(Icons.Filled.Lock, contentDescription = null)
            },
            trailingIcon = {
                val image = if (state.passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                IconButton(onClick = { onEventSent(SignInNavigator.Event.TogglePasswordVisibility) }) {
                    Icon(imageVector = image, "")
                }
            }
        )
        Spacer(modifier = Modifier.padding(vertical = ThemeConstants.PADDING.dp))
        Button(
            onClick = { onEventSent(SignInNavigator.Event.ToSignUpRequested) },
            contentPadding = PaddingValues(
                horizontal = 20.dp,
                vertical = 12.dp
            )
        ) {
            Text("To sign up")
        }
        Spacer(modifier = Modifier.padding(vertical = ThemeConstants.PADDING.dp))
        Button(
            onClick = {
                      onEventSent(SignInNavigator.Event.SignInRequested(state.email, state.password))
            },
            contentPadding = PaddingValues(
                horizontal = 20.dp,
                vertical = 12.dp
            )
        ) {
            Text("Sign in")
        }
    }
}

