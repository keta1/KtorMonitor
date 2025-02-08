package ro.cosminmihu.ktor.monitor.api.util

import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.readBytes
import io.ktor.client.statement.readRawBytes
import io.ktor.http.contentType
import ro.cosminmihu.ktor.monitor.ContentLength
import ro.cosminmihu.ktor.monitor.SanitizedHeader
import ro.cosminmihu.ktor.monitor.db.LibraryDao

internal fun logResponseException(
    dao: LibraryDao,
    id: String,
    cause: Throwable,
) {
    dao.saveResponse(
        id = id,
        error = cause,
    )
}

internal fun logResponse(
    dao: LibraryDao,
    id: String,
    response: HttpResponse,
    sanitizedHeaders: List<SanitizedHeader>,
) {
    val headers = response.headers.sanitizedHeaders(sanitizedHeaders)

    // Save response.
    dao.saveResponse(
        id = id,
        protocol = response.version.toString(),
        requestTimestamp = response.requestTime.timestamp,
        responseCode = response.status.value,
        responseTimestamp = response.responseTime.timestamp,
        responseContentType = response.contentType()?.toString(),
        responseHeaders = headers,
    )
}

internal suspend fun logResponseBody(
    dao: LibraryDao,
    id: String,
    maxContentLength: Int,
    response: HttpResponse,
) {
    // Read content.
    val responseBody = when {
        maxContentLength != ContentLength.Full -> response.readBytes(maxContentLength)
        else -> response.readRawBytes()
    }

    // Save response body.
    dao.saveResponseBody(
        id = id,
        responseContentLength = responseBody.size.toLong(),
        responseBody = responseBody,
        responseBodyTrimmed = responseBody.size > maxContentLength,
    )
}