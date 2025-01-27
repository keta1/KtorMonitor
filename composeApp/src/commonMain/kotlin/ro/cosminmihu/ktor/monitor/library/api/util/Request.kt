package ro.cosminmihu.ktor.monitor.library.api.util

import io.ktor.client.request.HttpRequestBuilder
import io.ktor.http.charset
import io.ktor.http.content.OutgoingContent
import io.ktor.utils.io.ByteChannel
import io.ktor.utils.io.charsets.Charsets
import io.ktor.utils.io.core.toByteArray
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import ro.cosminmihu.ktor.monitor.library.db.LibraryDao

internal fun logRequestException(
    dao: LibraryDao,
    id: String,
    cause: Throwable,
) {
    dao.saveRequest(
        id = id,
        error = cause,
    )
}

internal suspend fun logRequest(
    dao: LibraryDao,
    id: String,
    request: HttpRequestBuilder,
    coroutineScope: CoroutineScope,
    sanitizedHeaders: List<SanitizedHeader>,
): OutgoingContent? {
    val content = request.body as OutgoingContent

    // Headers.
    val url = request.url.toString()
    val method = request.method.value
    val headers = request.headers.sanitizedHeaders(sanitizedHeaders)
    val contentLength = content.contentLength ?: 0
    val contentType = content.contentType?.toString()

    // Body.
    val channel = ByteChannel()
    coroutineScope.launch(Dispatchers.Default) {
        val charset = content.contentType?.charset() ?: Charsets.UTF_8
        val text = channel.tryReadText(charset)?.toByteArray(charset)

        // Save request.
        dao.saveRequest(
            id = id,
            method = method,
            url = url,
            requestTime = Clock.System.now().toEpochMilliseconds(),
            requestHeaders = headers,
            requestContentType = contentType,
            requestSize = contentLength,
            requestBody = text,
        )
    }

    return content.observe(channel)
}