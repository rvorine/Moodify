package `in`.devpulse.moodify

import android.os.Build
import androidx.activity.ComponentActivity

class AndroidPlatform : Platform {
    override val name: String = "Android ${Build.VERSION.SDK_INT}"
}

actual fun getPlatform(): Platform = AndroidPlatform()

object ActivityContext {
    private lateinit var context: ComponentActivity
    fun setUp(context: ComponentActivity) {
        this.context = context
    }

    fun get(): ComponentActivity {
        if (::context.isInitialized.not()) throw Exception("Application context isn't initialized")
        return context
    }

    fun registerActivity() {

    }
}