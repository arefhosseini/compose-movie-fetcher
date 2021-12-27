package com.fearefull.composemoviefetcher.ui

import androidx.compose.runtime.Composable
import com.fearefull.composemoviefetcher.ui.theme.ComposeMovieFetcherTheme

/**
 * Created by Aref Hosseini on ۱۶/۱۱/۲۰۲۱.
 */

@Composable
fun MovieFetcherComposeApp(finishActivity: () -> Unit) {
    val appState = rememberMovieFetcherAppState(finishActivity)
    ComposeMovieFetcherTheme {
        AppNavigation(appState = appState)
    }
}
