package `in`.devpulse.moodify

import android.app.Application

class MoodifyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        ApplicationComponent.init()
    }
}