package com.fearefull.composemoviefetcher.ui.auth.sign_up

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fearefull.composemoviefetcher.base.LAUNCH_LISTEN_FOR_EFFECTS
import com.fearefull.composemoviefetcher.ui.theme.Purple200
import com.fearefull.composemoviefetcher.util.constants.ThemeConstants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

@Composable
fun SignUpScreen (
    state: SignUpNavigator.State,
    effectFlow: Flow<SignUpNavigator.Effect>?,
    onEventSent: (event: SignUpNavigator.Event) -> Unit,
    onNavigationSent: (navigationEffect: SignUpNavigator.Effect.Navigation) -> Unit,
) {
    LaunchedEffect(LAUNCH_LISTEN_FOR_EFFECTS) {
        effectFlow?.onEach { effect ->
            when(effect) {
                is SignUpNavigator.Effect.Navigation.ToMain ->
                    onNavigationSent(effect)
                is SignUpNavigator.Effect.Navigation.ToSignIn ->
                    onNavigationSent(effect)
                is SignUpNavigator.Effect.UserCreated -> {

                }
                is SignUpNavigator.Effect.ErrorUserCreated -> {

                }
            }
        }?.collect()
    }
    Column(
        // we are using column to align our
        // imageview to center of the screen.
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),

        // below line is used for specifying
        // vertical arrangement.
        verticalArrangement = Arrangement.Center,

        // below line is used for specifying
        // horizontal arrangement.
        horizontalAlignment = Alignment.CenterHorizontally,
    )
    {
        TextField(
            // below line is used to get
            // value of text field,
            value = state.email,

            // below line is used to get value in text field
            // on value change in text field.
            onValueChange = { onEventSent(SignUpNavigator.Event.OnEmailChange(it)) },

            // below line is used to add placeholder
            // for our text field.
            placeholder = { Text(text = "Enter email") },

            // modifier is use to add padding
            // to our text field.
            modifier = Modifier
                .padding(all = 16.dp)
                .fillMaxWidth(),

            // keyboard options is used to modify
            // the keyboard for text field.
            keyboardOptions = KeyboardOptions(
                // below line is use for capitalization
                // inside our text field.
                capitalization = KeyboardCapitalization.None,

                // below line is to enable auto
                // correct in our keyboard.
                autoCorrect = true,

                // below line is used to specify our
                // type of keyboard such as text, number, phone.
                keyboardType = KeyboardType.Text,
            ),

            // below line is use to specify
            // styling for our text field value.
            textStyle = TextStyle(color = Color.Black,
                // below line is used to add font
                // size for our text field
                fontSize = 15.sp,
            ),

            // single line boolean is use to avoid
            // textfield entering in multiple lines.
            singleLine = true,

            // leading icon is use to add icon
            // at the start of text field.
            leadingIcon = {
                // In this method we are specifying
                // our leading icon and its color.
                Icon(Icons.Filled.AccountCircle, contentDescription = null, tint = Purple200)
            },
            // trailing icons is use to add
            // icon to the end of tet field.
            trailingIcon = {
                Icon(Icons.Filled.Info, contentDescription = null, tint = Purple200)
            },
        )
        TextField(
            // below line is used to get
            // value of text field,
            value = state.password,

            // below line is used to get value in text field
            // on value change in text field.
            onValueChange = { onEventSent(SignUpNavigator.Event.OnPasswordChange(it)) },

            // below line is used to add placeholder
            // for our text field.
            placeholder = { Text(text = "Enter password") },

            // modifier is use to add padding
            // to our text field.
            modifier = Modifier
                .padding(all = 16.dp)
                .fillMaxWidth(),

            // keyboard options is used to modify
            // the keyboard for text field.
            keyboardOptions = KeyboardOptions(
                // below line is use for capitalization
                // inside our text field.
                capitalization = KeyboardCapitalization.None,

                // below line is to enable auto
                // correct in our keyboard.
                autoCorrect = true,

                // below line is used to specify our
                // type of keyboard such as text, number, phone.
                keyboardType = KeyboardType.Password,
            ),

            // below line is use to specify
            // styling for our text field value.
            textStyle = TextStyle(color = Color.Black,
                // below line is used to add font
                // size for our text field
                fontSize = 15.sp,
            ),

            // single line boolean is use to avoid
            // textfield entering in multiple lines.
            singleLine = true,

            // leading icon is use to add icon
            // at the start of text field.
            leadingIcon = {
                // In this method we are specifying
                // our leading icon and its color.
                Icon(Icons.Filled.AccountCircle, contentDescription = null, tint = Purple200)
            },
            // trailing icons is use to add
            // icon to the end of tet field.
            trailingIcon = {
                Icon(Icons.Filled.Info, contentDescription = null, tint = Purple200)
            },
        )
        Spacer(modifier = Modifier.padding(vertical = ThemeConstants.PADDING.dp))
        Button(
            onClick = { onEventSent(SignUpNavigator.Event.ToSignInRequested) },
            contentPadding = PaddingValues(
                horizontal = 20.dp,
                vertical = 12.dp
            )
        ) {
            Text("To sign in")
        }
        Spacer(modifier = Modifier.padding(vertical = ThemeConstants.PADDING.dp))
        Button(
            onClick = {
                onEventSent(SignUpNavigator.Event.SignUpRequested(state.email, state.password))
            },
            contentPadding = PaddingValues(
                horizontal = 20.dp,
                vertical = 12.dp
            )
        ) {
            Text("Sign up")
        }
    }
}