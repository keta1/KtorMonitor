package ro.cosminmihu.ktor.monitor.library.ui.detail

import androidx.compose.ui.text.AnnotatedString
import ro.cosminmihu.ktor.monitor.library.domain.model.ContentType

data class DetailUiState(
    val call: Call? = null,
    val summary: Summary? = null,
) {
    data class Call(
        val id: String,
        val isSecure: Boolean,
        val request: Request,
        val response: Response,
    )

    data class Summary(
        val url: String,
        val method: String,
        val protocol: String,
        val requestTime: String,
        val responseCode: String,
        val responseTime: String,
        val duration: String,
        val requestSize: String,
        val responseSize: String,
        val totalSize: String,
    )

    data class Request(
        val method: String,
        val host: String,
        val pathAndQuery: String,
        val requestTime: String,
        val headers: Map<String, List<String>>,
        val body: Body,
    )

    data class Response(
        val responseCode: String,
        val contentType: ContentType,
        val duration: String,
        val size: String,
        val error: String,
        val headers: Map<String, List<String>>,
        val body: Body,
    )

    data class Body(
        val bytes: AnnotatedString?,
        val raw: AnnotatedString?,
        val code: AnnotatedString?,
        val image: ByteArray?,
        val html: AnnotatedString?,
    )
}

val DetailUiState.Response.isLoading
    get() = responseCode.isBlank() && error.isBlank()

val DetailUiState.Response.isError
    get() = responseCode.isBlank() && error.isNotBlank()
