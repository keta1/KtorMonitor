package ro.cosminmihu.ktor.monitor

import io.ktor.client.plugins.api.ClientPlugin
import ro.cosminmihu.ktor.monitor.api.LoggingPlugin
import kotlin.time.Duration
import kotlin.time.Duration.Companion.days
import kotlin.time.Duration.Companion.hours

/**
 * The retention period for the logs.
 */
public object RetentionPeriod {
    public val OneHour: Duration = 1.hours
    public val OneDay: Duration = 1.days
    public val OneWeek: Duration = OneDay * 7
    public val Forever: Duration = Duration.INFINITE
}

/**
 * The maximum length of the content that will be logged.
 * After this response body will be truncated.
 */
public object ContentLength {
    /**
     * The default value for the maximum length of the content that will be logged.
     */
    public const val Default: Int = 250_000

    /**
     * The content will not be truncated.
     */
    public const val Full: Int = Int.MAX_VALUE
}

/**
 * A [Ktor](https://ktor.io/) client plugin that provides the capability to log HTTP calls.
 *
 * You can learn more from [KtorMonitor](https://github.com/CosminMihuMDC/KtorMonitor).
 *
 * ```kotlin
 * HttpClient {
 *    install(KtorMonitorLogging) {
 *       sanitizeHeader { header -> header == "Authorization" }
 *       filter { request -> !request.url.host.contains("cosminmihu.ro") }
 *       isActive = true
 *       showNotification = true
 *       retentionPeriod = RetentionPeriod.OneHour
 *    }
 * }
 * ```
 */
public val KtorMonitorLogging: ClientPlugin<KtorMonitorLoggingConfig> = LoggingPlugin