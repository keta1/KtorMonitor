package ro.cosminmihu.ktor.monitor.sample

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.androix.startup.KoinStartup.onKoinStartup
import ro.cosminmihu.ktor.monitor.library.di.KtorMonitor

class SampleApp : Application() {

    init {
        KtorMonitor.init(this)

        onKoinStartup {
            androidContext(this@SampleApp)
        }
    }
}