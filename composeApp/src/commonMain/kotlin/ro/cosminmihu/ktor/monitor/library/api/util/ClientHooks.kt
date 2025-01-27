package ro.cosminmihu.ktor.monitor.library.api.util

import io.ktor.client.HttpClient
import io.ktor.client.call.HttpClientCall
import io.ktor.client.plugins.api.ClientHook
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.HttpSendPipeline
import io.ktor.client.statement.HttpReceivePipeline
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.HttpResponseContainer
import io.ktor.client.statement.HttpResponsePipeline
import io.ktor.util.pipeline.PipelineContext
import io.ktor.util.pipeline.PipelinePhase

object ResponseHook :
    ClientHook<suspend ResponseHook.Context.(response: HttpResponse) -> Unit> {

    class Context(private val context: PipelineContext<HttpResponse, Unit>) {
        suspend fun proceed() = context.proceed()
    }

    override fun install(
        client: HttpClient,
        handler: suspend Context.(response: HttpResponse) -> Unit,
    ) {
        client.receivePipeline.intercept(HttpReceivePipeline.State) {
            handler(Context(this), subject)
        }
    }
}

object ResponseAfterEncodingHook :
    ClientHook<suspend ResponseAfterEncodingHook.Context.(response: HttpResponse) -> Unit> {

    class Context(private val context: PipelineContext<HttpResponse, Unit>) {
        suspend fun proceedWith(response: HttpResponse) = context.proceedWith(response)
    }

    override fun install(
        client: HttpClient,
        handler: suspend Context.(response: HttpResponse) -> Unit,
    ) {
        val afterState = PipelinePhase("AfterState")
        client.receivePipeline.insertPhaseAfter(HttpReceivePipeline.State, afterState)
        client.receivePipeline.intercept(afterState) {
            handler(Context(this), subject)
        }
    }
}

object SendHook :
    ClientHook<suspend SendHook.Context.(response: HttpRequestBuilder) -> Unit> {

    class Context(private val context: PipelineContext<Any, HttpRequestBuilder>) {
        suspend fun proceedWith(content: Any) = context.proceedWith(content)
        suspend fun proceed() = context.proceed()
    }

    override fun install(
        client: HttpClient,
        handler: suspend Context.(request: HttpRequestBuilder) -> Unit,
    ) {
        client.sendPipeline.intercept(HttpSendPipeline.Monitoring) {
            handler(Context(this), context)
        }
    }
}

object ReceiveHook :
    ClientHook<suspend ReceiveHook.Context.(call: HttpClientCall) -> Unit> {

    class Context(private val context: PipelineContext<HttpResponseContainer, HttpClientCall>) {
        suspend fun proceed() = context.proceed()
    }

    override fun install(
        client: HttpClient,
        handler: suspend Context.(call: HttpClientCall) -> Unit,
    ) {
        client.responsePipeline.intercept(HttpResponsePipeline.Receive) {
            handler(Context(this), context)
        }
    }
}