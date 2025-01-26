package ro.cosminmihu.ktor.monitor.library.api

import io.ktor.client.call.HttpClientCall
import io.ktor.client.request.HttpRequest
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.http.Headers
import io.ktor.http.HttpMethod
import io.ktor.http.Url
import io.ktor.http.content.OutgoingContent
import io.ktor.util.Attributes

// https://github.com/ktorio/ktor/blob/a870be2c65da3ffe0cb88e6904a4bc140541b6b7/ktor-client/ktor-client-core/common/src/io/ktor/client/plugins/HttpCallValidator.kt#L117
internal fun HttpRequest(builder: HttpRequestBuilder): HttpRequest = object : HttpRequest {
    override val call: HttpClientCall get() = error("Call is not initialized")
    override val method: HttpMethod = builder.method
    override val url: Url = builder.url.build()
    override val attributes: Attributes = builder.attributes
    override val headers: Headers = builder.headers.build()
    override val content: OutgoingContent
        get() = builder.body as? OutgoingContent
            ?: error("Content was not transformed to OutgoingContent yet. Current body is ${builder.body}")
}
