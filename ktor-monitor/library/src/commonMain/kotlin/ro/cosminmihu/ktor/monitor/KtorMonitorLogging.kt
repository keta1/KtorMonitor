package ro.cosminmihu.ktor.monitor

import io.ktor.client.plugins.api.ClientPlugin
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.utils.io.KtorDsl
import ro.cosminmihu.ktor.monitor.api.LoggingPlugin

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

/**
 * A configuration for the [KtorMonitorLogging] plugin.
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
}

/**
 * Configuration for a sanitized header.
 */
internal class SanitizedHeader(
    val placeholder: String,
    val predicate: (String) -> Boolean,
)