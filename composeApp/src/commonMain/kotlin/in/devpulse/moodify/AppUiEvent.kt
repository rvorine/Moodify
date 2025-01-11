package `in`.devpulse.moodify

sealed class AppUiEvent {
    data object onAuthenticate : AppUiEvent()
}