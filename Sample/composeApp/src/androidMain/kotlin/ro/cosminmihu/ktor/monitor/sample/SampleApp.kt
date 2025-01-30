package ro.cosminmihu.ktor.monitor.sample

import android.app.Application
import ro.cosminmihu.ktor.monitor.KtorMonitor

/**
 * Sample Application to initialize [KtorMonitor].
 */
class SampleApp : Application() {

    init {
        KtorMonitor.init(this)
    }
}