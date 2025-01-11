package `in`.devpulse.moodify.repository

interface Spotify {
    fun authenticateSpotify(onResult: (String?) -> Unit)
}

expect fun getSpotifyClient(): Spotify