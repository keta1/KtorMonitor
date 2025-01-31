package ro.cosminmihu.ktor.monitor.core

import android.content.Context
import ro.cosminmihu.ktor.monitor.di.LibraryKoinContext

internal actual typealias PlatformContext = Context

internal actual fun applyPlatformContext() {
    /**
     * PlatformContext should be already injected using [LibraryInitializer].
     */

    /**
     * Check if the context is set. If not, throw an exception.
     */
    checkNotNull(LibraryKoinContext.koin.getOrNull<PlatformContext>()) { "Context is not set." }
}