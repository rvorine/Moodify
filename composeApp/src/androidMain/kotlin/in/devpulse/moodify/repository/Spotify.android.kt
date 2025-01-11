package `in`.devpulse.moodify.repository

import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import com.spotify.sdk.android.auth.AuthorizationClient
import com.spotify.sdk.android.auth.AuthorizationRequest
import com.spotify.sdk.android.auth.AuthorizationResponse
import `in`.devpulse.moodify.ActivityContext
import `in`.devpulse.moodify.BuildConfig


class SpotifyAuthenticator(
    private val context: ComponentActivity
) : Spotify {

    private var onResultCallback: ((String?) -> Unit)? = null

    private val activityResultLauncher by lazy {
        context.activityResultRegistry.register(
            "SpotifyActivityForResult",
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            val response: AuthorizationResponse =
                AuthorizationClient.getResponse(result.resultCode, result.data)
            when (response.type) {
                AuthorizationResponse.Type.TOKEN -> onResultCallback?.invoke(response.accessToken)
                else -> {
                    Log.e("SPOTIFY", response.error)
                    onResultCallback?.invoke(null)
                }
            }
        }
    }

    override fun authenticateSpotify(onResult: (String?) -> Unit) {
        onResultCallback = onResult
        val authBuilder = AuthorizationRequest.Builder(
            BuildConfig.SPOTIFY_CLIENT_KEY,
            AuthorizationResponse.Type.TOKEN,
            "https://devpulse.in/callback"
        )
        activityResultLauncher.launch(
            AuthorizationClient.createLoginActivityIntent(
                context,
                authBuilder.build()
            )
        )
    }
}

actual fun getSpotifyClient(): Spotify = SpotifyAuthenticator(ActivityContext.get())