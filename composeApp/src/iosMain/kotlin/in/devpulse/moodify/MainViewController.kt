package `in`.devpulse.moodify

import androidx.compose.ui.window.ComposeUIViewController

fun initialize() {
    ApplicationComponent.init()
}

fun MainViewController() = ComposeUIViewController { App() }