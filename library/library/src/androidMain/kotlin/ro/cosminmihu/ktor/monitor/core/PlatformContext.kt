package ro.cosminmihu.ktor.monitor.core

import android.content.Context
import ro.cosminmihu.ktor.monitor.KtorMonitor

internal actual typealias PlatformContext = Context

internal actual fun applyPlatformContext() {
    /**
     * PlatformContext is already injected in [KtorMonitor.init]
     */
}