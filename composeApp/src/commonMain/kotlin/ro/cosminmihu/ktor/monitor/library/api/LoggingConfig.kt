package ro.cosminmihu.ktor.monitor.library.api


import io.ktor.client.request.HttpRequestBuilder
import io.ktor.utils.io.KtorDsl
import kotlin.time.Duration
import kotlin.time.Duration.Companion.days
import kotlin.time.Duration.Companion.hours

/**
 * A configuration for the [LoggingPlugin] plugin.
 */
@KtorDsl
class LoggingConfig {
    internal val filters = mutableListOf<(HttpRequestBuilder) -> Boolean>()
    internal val sanitizedHeaders = mutableListOf<SanitizedHeader>()

    /**
     * Allows you to filter log messages for calls matching a [predicate].
     */
    fun filter(predicate: (HttpRequestBuilder) -> Boolean) {
        filters.add(predicate)
    }

    /**
     * Allows you to sanitize sensitive headers to avoid their values appearing in the logs.
     * In the example below, Authorization header value will be replaced with '***' when logging:
     * ```kotlin
     * sanitizeHeader { header -> header == HttpHeaders.Authorization }
     * ```
     */
    fun sanitizeHeader(placeholder: String = "***", predicate: (String) -> Boolean) {
        sanitizedHeaders.add(SanitizedHeader(placeholder, predicate))
    }

    /**
     * Allows to disable the logging of requests and responses.
     */
    var isActive: Boolean = true

    /**
     * Keep track of latest requests and responses into notification.
     * Android only.
     */
    var showNotification: Boolean = true

    /**
     * The retention period for the logs.
     */
    var retentionPeriod = RetentionPeriod.OneHour
}

/**
 * Configuration for a sanitized header.
 */
class SanitizedHeader(
    val placeholder: String,
    val predicate: (String) -> Boolean,
)

/**
 * The retention period for the logs.
 */
object RetentionPeriod {
    val OneHour = 1.hours
    val OneDay = 1.days
    val OneWeek = OneDay * 7
    val Forever = Duration.INFINITE
}
