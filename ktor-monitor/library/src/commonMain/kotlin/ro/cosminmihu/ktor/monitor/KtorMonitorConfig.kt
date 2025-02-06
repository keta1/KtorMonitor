package ro.cosminmihu.ktor.monitor

import ro.cosminmihu.ktor.monitor.core.PlatformDebug
import ro.cosminmihu.ktor.monitor.di.LibraryKoinContext
import kotlin.time.Duration
import kotlin.time.Duration.Companion.days
import kotlin.time.Duration.Companion.hours

/**
 * A configuration for the Ktor Monitor.
 */
public data class KtorMonitorConfig(

    /**
     * Enable or disable the logging of requests and responses.
     * By default it is enabled in debug mode only.
     */
    public var isActive: Boolean = PlatformDebug.isDebug,

    /**
     * Keep track of latest requests and responses into notification.
     * *** Android only.
     */
    public var showNotification: Boolean = true,

    /**
     * The retention period for the logs.
     */
    public var retentionPeriod: Duration = RetentionPeriod.OneHour,
) {

    /**
     * The retention period for the logs.
     */
    public object RetentionPeriod {
        public val OneHour: Duration = 1.hours
        public val OneDay: Duration = 1.days
        public val OneWeek: Duration = OneDay * 7
        public val Forever: Duration = Duration.INFINITE
    }
}

/**
 * Initializes the Ktor Monitor.
 */
public object KtorMonitor {

    public fun init(config: KtorMonitorConfig) {
        LibraryKoinContext.setLibraryConfig(config)
    }
}