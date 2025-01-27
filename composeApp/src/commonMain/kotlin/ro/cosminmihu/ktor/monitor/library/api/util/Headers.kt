package ro.cosminmihu.ktor.monitor.library.api.util

import io.ktor.http.Headers
import io.ktor.http.HeadersBuilder

internal class SanitizedHeader(
    val placeholder: String,
    val predicate: (String) -> Boolean,
)

internal fun HeadersBuilder.sanitizedHeaders(
    sanitizedHeaders: List<SanitizedHeader>,
): Map<String, List<String>> {
    val sortedHeaders = this.entries().toList().sortedBy { it.key }

    return sortedHeaders.associate { (key, values) ->
        val placeholder = sanitizedHeaders
            .firstOrNull { it.predicate(key) }
            ?.placeholder
            ?.let { listOf(it) }
        Pair(key, placeholder ?: values)
    }
}

internal fun Headers.sanitizedHeaders(
    sanitizedHeaders: List<SanitizedHeader>,
): Map<String, List<String>> {
    val sortedHeaders = this.entries().toList().sortedBy { it.key }

    return sortedHeaders.associate { (key, values) ->
        val placeholder = sanitizedHeaders
            .firstOrNull { it.predicate(key) }
            ?.placeholder
            ?.let { listOf(it) }
        Pair(key, placeholder ?: values)
    }
}