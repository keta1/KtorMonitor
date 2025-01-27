package ro.cosminmihu.ktor.monitor.library.api.util

import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.readRawBytes
import io.ktor.http.contentType
import ro.cosminmihu.ktor.monitor.library.db.LibraryDao

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
    val responseStatus = response.status.value
    val headers = response.headers.sanitizedHeaders(sanitizedHeaders)

    // Save response.
    dao.saveResponse(
        id = id,
        responseCode = responseStatus,
        requestTime = response.requestTime.timestamp,
        responseTime = response.responseTime.timestamp,
        responseContentType = response.contentType()?.toString(),
        responseHeaders = headers,
        protocol = response.version.toString(),
    )
}

internal suspend fun logResponseBody(
    dao: LibraryDao,
    id: String,
    response: HttpResponse,
) {
    // Save response body.
    val responseBody = response.readRawBytes()
    dao.saveResponseBody(
        id = id,
        responseSize = responseBody.size.toLong(),
        responseBody = responseBody,
    )
}