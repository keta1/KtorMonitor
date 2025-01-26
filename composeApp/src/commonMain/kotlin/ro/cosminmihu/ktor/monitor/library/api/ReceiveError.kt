package ro.cosminmihu.ktor.monitor.library.api

import io.ktor.client.HttpClient
import io.ktor.client.plugins.api.ClientHook
import io.ktor.client.request.HttpRequest
import io.ktor.client.statement.HttpResponsePipeline
import io.ktor.util.pipeline.PipelinePhase

// https://github.com/ktorio/ktor/blob/a870be2c65da3ffe0cb88e6904a4bc140541b6b7/ktor-client/ktor-client-core/common/src/io/ktor/client/plugins/HttpCallValidator.kt#L130
internal object ReceiveError : ClientHook<suspend (HttpRequest, Throwable) -> Throwable?> {
    override fun install(
        client: HttpClient,
        handler: suspend (HttpRequest, Throwable) -> Throwable?,
    ) {
        val BeforeReceive = PipelinePhase("BeforeReceive")
        client.responsePipeline.insertPhaseBefore(HttpResponsePipeline.Receive, BeforeReceive)
        client.responsePipeline.intercept(BeforeReceive) {
            try {
                proceed()
            } catch (cause: Throwable) {
                val error = handler(context.request, cause)
                if (error != null) throw error
            }
        }
    }
}