package ro.cosminmihu.ktor.monitor


import io.ktor.client.request.HttpRequestBuilder
import io.ktor.utils.io.KtorDsl
import kotlin.time.Duration
import kotlin.time.Duration.Companion.days
import kotlin.time.Duration.Companion.hours

/**
 * A configuration for the [ro.cosminmihu.ktor.monitor.api.LoggingPlugin] plugin.
 */
@KtorDsl
public class KtorMonitorLoggingConfig {
    internal val filters = mutableListOf<(HttpRequestBuilder) -> Boolean>()
    internal val sanitizedHeaders = mutableListOf<SanitizedHeader>()

    /**
     * Allows you to filter log messages for calls matching a [predicate].
     */
    public fun filter(predicate: (HttpRequestBuilder) -> Boolean) {
        filters.add(predicate)
    }

    /**
     * Allows you to sanitize sensitive headers to avoid their values appearing in the logs.
     * In the example below, Authorization header value will be replaced with '***' when logging:
     * ```kotlin
     * sanitizeHeader { header -> header == HttpHeaders.Authorization }
     * ```
     */
    public fun sanitizeHeader(placeholder: String = "***", predicate: (String) -> Boolean) {
        sanitizedHeaders.add(SanitizedHeader(placeholder, predicate))
    }

    /**
     * Allows to disable the logging of requests and responses.
     */
    public var isActive: Boolean = true

    /**
     * Keep track of latest requests and responses into notification.
     * Android only.
     */
    public var showNotification: Boolean = true

    /**
     * The retention period for the logs.
     */
    public var retentionPeriod: Duration = RetentionPeriod.OneHour
}

/**
 * Configuration for a sanitized header.
 */
internal class SanitizedHeader(
    val placeholder: String,
    val predicate: (String) -> Boolean,
)

/**
 * The retention period for the logs.
 */
public object RetentionPeriod {
    public val OneHour: Duration = 1.hours
    public val OneDay: Duration = 1.days
    public val OneWeek: Duration = OneDay * 7
    public val Forever: Duration = Duration.INFINITE
}