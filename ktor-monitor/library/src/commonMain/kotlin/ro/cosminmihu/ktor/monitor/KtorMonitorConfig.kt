package ro.cosminmihu.ktor.monitor

import io.ktor.client.request.HttpRequestBuilder
import io.ktor.utils.io.KtorDsl
import kotlin.time.Duration

/**
 * A configuration for the [KtorMonitorLogging] plugin.
 */
@KtorDsl
public class KtorMonitorConfig {
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
     * Enable or disable the logging of requests and responses.
     * Enabled by default.
     */
    public var isActive: Boolean = true

    /**
     * Keep track of latest requests and responses into notification.
     * *** Android only.
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