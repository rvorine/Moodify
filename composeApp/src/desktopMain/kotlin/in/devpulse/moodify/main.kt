package `in`.devpulse.moodify

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    ApplicationComponent.init()
    Window(
        onCloseRequest = ::exitApplication,
        title = "Moodify",
    ) {
        App()
    }
}