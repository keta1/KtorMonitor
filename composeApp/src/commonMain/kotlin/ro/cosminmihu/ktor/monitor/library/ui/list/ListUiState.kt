package ro.cosminmihu.ktor.monitor.library.ui.list

import ro.cosminmihu.ktor.monitor.library.domain.model.ContentType

data class ListUiState(
    val calls: List<Call>? = null,
    val searchQuery: String = "",
) {
    data class Call(
        val id: String,
        val isSecure: Boolean,
        val request: Request,
        val response: Response,
    )

    data class Request(
        val method: String,
        val host: String,
        val pathAndQuery: String,
        val requestTime: String,
    )

    data class Response(
        val responseCode: String,
        val contentType: ContentType,
        val duration: String,
        val size: String,
        val error: String,
    )
}

val ListUiState.isLoading
    get() = calls == null

val ListUiState.isEmpty
    get() = calls.isNullOrEmpty()

val ListUiState.Call.isLoading
    get() = response.responseCode.isBlank() && response.error.isBlank()

val ListUiState.Call.isError
    get() = response.responseCode.isBlank() && response.error.isNotBlank()
