package ro.cosminmihu.ktor.monitor.core

import ro.cosminmihu.ktor.monitor.BuildConfig

internal actual object PlatformConfig {

    actual val isDebug: Boolean = BuildConfig.DEBUG
}