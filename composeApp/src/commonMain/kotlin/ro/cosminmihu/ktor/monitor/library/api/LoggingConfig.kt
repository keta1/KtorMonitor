package ro.cosminmihu.ktor.monitor.library.api


import io.ktor.client.request.HttpRequestBuilder
import io.ktor.utils.io.KtorDsl
import ro.cosminmihu.ktor.monitor.library.api.util.SanitizedHeader

/**
 * A configuration for the [LoggingPlugin] plugin.
 */
@KtorDsl
class LoggingConfig {
    internal val filters = mutableListOf<(HttpRequestBuilder) -> Boolean>()
    internal val sanitizedHeaders = mutableListOf<SanitizedHeader>()
    internal var isActive = true

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
    var active: Boolean
        get() = isActive
        set(value) {
            isActive = value
        }
}