package ro.cosminmihu.ktor.monitor.library.api

import io.ktor.client.plugins.api.SendingRequest
import io.ktor.client.plugins.api.createClientPlugin
import io.ktor.client.request.HttpRequest
import io.ktor.client.statement.bodyAsBytes
import io.ktor.client.utils.unwrapCancellationException
import io.ktor.http.HeadersBuilder
import io.ktor.http.HttpHeaders
import io.ktor.util.AttributeKey
import io.ktor.util.toMap
import kotlinx.datetime.Clock
import ro.cosminmihu.ktor.monitor.library.db.LibraryDao
import ro.cosminmihu.ktor.monitor.library.di.LibraryKoinContext
import ro.cosminmihu.ktor.monitor.library.di.get
import kotlin.random.Random

private val CallIdentifier = AttributeKey<String>("KtorMonitorCallIdentifier")
private const val PluginName = "KtorMonitorLogging"

val KtorMonitorLogging = createClientPlugin(PluginName) {

    // Init library dependency inject.
    LibraryKoinContext.init()

    // Cache request data.
    on(SendingRequest) { request, content ->
        // Generate and save id.
        val callIdentifier = request.attributes.computeIfAbsent(CallIdentifier) { callIdentifier }

        // Save request in database.
        val dao = LibraryKoinContext.get<LibraryDao>()
        dao.saveRequest(
            id = callIdentifier,
            method = request.method.value,
            url = request.url.toString(),
            requestTime = Clock.System.now().toEpochMilliseconds(),
            requestSize = content.contentLength ?: 0L,
            requestHeaders = request.headers.toMap(),
            requestContentType = request.headers[HttpHeaders.ContentType],
            requestBody = null,
//            requestBody = when (content) {
//                is OutgoingContent.ByteArrayContent -> content.bytes()
//                is OutgoingContent.ReadChannelContent -> content.readFrom().readRemaining()
//                    .readByteArray()
//
//                is OutgoingContent.WriteChannelContent -> null // TODO
//                is OutgoingContent.ContentWrapper -> null // TODO
//                is OutgoingContent.NoContent,
//                is OutgoingContent.ProtocolUpgrade,
//                    -> null
//            },
        )
    }

    onRequest { request, _ ->
        // Generate and save id.
        request.attributes.computeIfAbsent(CallIdentifier) { callIdentifier }
    }

    // Cache response data.
    onResponse { response ->
        val callIdentifier = response.call.attributes[CallIdentifier]

        // Save response in database.
        val dao = LibraryKoinContext.get<LibraryDao>()
        dao.saveResponse(
            id = callIdentifier,
            responseCode = response.status.value,
            requestTime = response.requestTime.timestamp,
            responseTime = response.responseTime.timestamp,
            responseSize = response.bodyAsBytes().size.toLong(),
            responseContentType = response.headers[HttpHeaders.ContentType],
            responseHeaders = response.headers.toMap(),
            responseBody = response.bodyAsBytes(),
            protocol = response.version.toString()
        )
    }

    // Cache request error.
    on(RequestError) { request, cause ->
        val unwrappedCause = cause.unwrapCancellationException()
        processException(unwrappedCause, request)
        unwrappedCause
    }

    // Cache response error.
    on(ReceiveError) { request, cause ->
        val unwrappedCause = cause.unwrapCancellationException()
        processException(unwrappedCause, request)
        unwrappedCause
    }
}

private val callIdentifier
    get() = Clock.System.now().toEpochMilliseconds().toString() + "-" + Random.nextLong()

private fun processException(cause: Throwable, request: HttpRequest) {
    val callIdentifier = request.attributes[CallIdentifier]

    // Save response in database.
    val dao = LibraryKoinContext.get<LibraryDao>()
    dao.saveResponse(
        id = callIdentifier,
        error = cause,
    )
}

private fun HeadersBuilder.toMap(): Map<String, List<String>> =
    entries().associate { it.key to it.value }