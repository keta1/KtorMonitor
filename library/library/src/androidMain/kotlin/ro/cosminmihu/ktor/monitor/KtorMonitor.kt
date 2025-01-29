package ro.cosminmihu.ktor.monitor

import ro.cosminmihu.ktor.monitor.core.PlatformContext
import ro.cosminmihu.ktor.monitor.di.LibraryKoinContext

/**
 * Init.
 */
public object KtorMonitor {

    public fun init(context: PlatformContext) {
        LibraryKoinContext.set(context)
    }
}