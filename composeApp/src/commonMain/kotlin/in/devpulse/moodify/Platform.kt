package `in`.devpulse.moodify

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform