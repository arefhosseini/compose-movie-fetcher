package com.fearefull.composemoviefetcher.buildsrc

object Dependencies {
    const val ktlint = "com.pinterest:ktlint:0.42.1"
    const val coil = "io.coil-kt:coil-compose:2.2.0"
    const val daggerHiltVersion = "2.43.2"

    object Kotlin {
        const val version = "1.7.10"
        const val stdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${version}"
        const val jsonSerialization = "org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.0"
    }

    object Gradle {
        const val androidBuildPlugin = "com.android.tools.build:gradle:7.2.2"
        const val hiltPlugin = "com.google.dagger:hilt-android-gradle-plugin:$daggerHiltVersion"
        const val kotlinPlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Kotlin.version}"
        const val kotlinSerializationPlugin = "org.jetbrains.kotlin:kotlin-serialization:${Kotlin.version}"
        const val firebasePlugin = "com.google.gms:google-services:4.3.13"

        object VersionsPlugin {
            const val id = "com.github.ben-manes.versions"
            const val version = "0.42.0"
        }
    }

    object AndroidX {
        const val palette = "androidx.palette:palette-ktx:1.0.0"
        const val browser = "androidx.browser:browser:1.4.0-rc01"
        const val dataStore = "androidx.datastore:datastore-preferences:1.0.0"

        object Ktx {
            const val core = "androidx.core:core-ktx:1.8.0"
        }
    }

    object Compose {
        // Compose is combination of 7 Maven Group Ids within androidx.
        // Each Group contains a targeted subset of functionality, each with it's own set of release notes.
        const val version = "1.2.1"
        const val compilerVersion = "1.3.0"
        const val runtime = "androidx.compose.runtime:runtime:$version"
        const val foundation = "androidx.compose.foundation:foundation:$version"
        const val layout = "androidx.compose.foundation:foundation-layout:$version"
        const val ui = "androidx.compose.ui:ui:$version"
        const val uiUtil = "androidx.compose.ui:ui-util:$version"
        const val material = "androidx.compose.material:material:$version"
        const val icons = "androidx.compose.material:material-icons-extended:$version"
        const val animation = "androidx.compose.animation:animation:$version"
        const val tooling = "androidx.compose.ui:ui-tooling:$version"
        const val uiTest = "androidx.compose.ui:ui-test:$version"
        const val uiTestJunit = "androidx.compose.ui:ui-test-junit4:$version"
        const val paging = "androidx.paging:paging-compose:1.0.0-alpha16"
        const val activity = "androidx.activity:activity-compose:1.5.1"
        const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-compose:2.6.0-alpha01"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout-compose:1.0.1"

        object Accompanist {
            private const val accompanistLibrary = "com.google.accompanist:accompanist"
            private const val version = "0.26.2-beta"

            const val insets = "$accompanistLibrary-insets:$version"
            const val insetsUi = "$accompanistLibrary-insets-ui:$version"
            const val pager = "$accompanistLibrary-pager:$version"
        }

        object Navigation {
            private const val version = "2.5.1"
            const val runtime = "androidx.navigation:navigation-compose:$version"
        }

        object HiltNavigation {
            private const val version = "1.0.0"
            const val runtime = "androidx.hilt:hilt-navigation-compose:$version"
        }
    }

    object Hilt {
        const val android = "com.google.dagger:hilt-android:$daggerHiltVersion"
        const val androidCompiler = "com.google.dagger:hilt-android-compiler:$daggerHiltVersion"
        const val viewmodelCompiler = "androidx.hilt:hilt-compiler:1.0.0"
    }

    object Retrofit {
        private const val version = "2.9.0"
        const val runtime = "com.squareup.retrofit2:retrofit:$version"
        const val gsonConverter = "com.squareup.retrofit2:converter-gson:$version"

        const val interceptor = "com.squareup.okhttp3:logging-interceptor:4.10.0"
    }

    object Test {
        const val junit = "junit:junit:4.13.2"
        const val mockk = "io.mockk:mockk:1.12.0"
        const val striktAssertion = "io.strikt:strikt-core:0.34.0"
        const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.4"
        const val junitExt = "androidx.test.ext:junit:1.1.3"
    }

    object Timber {
        private const val version = "5.0.1"
        const val runtime = "com.jakewharton.timber:timber:$version"
    }

    object Room {
        private const val version = "2.4.3"
        const val runtime = "androidx.room:room-runtime:$version"
        const val compiler = "androidx.room:room-compiler:$version"
    }

    object StartUp {
        private const val version = "1.1.1"
        const val runtime = "androidx.startup:startup-runtime:$version"
    }

    object Firebase {
        private const val version = "21.0.7"
        const val auth = "com.google.firebase:firebase-auth:$version"
        const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-play-services:1.6.4"
    }
}
