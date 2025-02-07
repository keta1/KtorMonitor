package ro.cosminmihu.ktor.monitor.core

import ro.cosminmihu.ktor.monitor.di.LibraryKoinContext

internal actual abstract class PlatformContext private constructor() {
    companion object {
        val INSTANCE = object : PlatformContext() {}
    }
}

internal actual fun applyPlatformContext() {
    LibraryKoinContext.setPlatformContext(PlatformContext.INSTANCE)
}