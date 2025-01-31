package ro.cosminmihu.ktor.monitor.ui.list

import ro.cosminmihu.ktor.monitor.domain.model.ContentType

internal data class ListUiState(
    val calls: List<Call>? = null,
    val searchQuery: String = "",
    val showNotification: Boolean = false,
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

internal val ListUiState.isLoading
    get() = calls == null

internal val ListUiState.isEmpty
    get() = calls.isNullOrEmpty()

internal val ListUiState.Call.isLoading
    get() = response.responseCode.isBlank() && response.error.isBlank()

internal val ListUiState.Call.isError
    get() = response.responseCode.isBlank() && response.error.isNotBlank()
