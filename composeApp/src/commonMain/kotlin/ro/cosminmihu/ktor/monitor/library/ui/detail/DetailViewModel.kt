package ro.cosminmihu.ktor.monitor.library.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import ro.cosminmihu.ktor.monitor.library.domain.GetCallUseCase
import ro.cosminmihu.ktor.monitor.library.domain.model.ContentType
import ro.cosminmihu.ktor.monitor.library.domain.model.contentType
import ro.cosminmihu.ktor.monitor.library.domain.model.durationAsText
import ro.cosminmihu.ktor.monitor.library.domain.model.encodedPathAndQuery
import ro.cosminmihu.ktor.monitor.library.domain.model.host
import ro.cosminmihu.ktor.monitor.library.domain.model.isError
import ro.cosminmihu.ktor.monitor.library.domain.model.isInProgress
import ro.cosminmihu.ktor.monitor.library.domain.model.isSecure
import ro.cosminmihu.ktor.monitor.library.domain.model.requestDateTimeAsText
import ro.cosminmihu.ktor.monitor.library.domain.model.requestTimeAsText
import ro.cosminmihu.ktor.monitor.library.domain.model.responseDateTimeAsText
import ro.cosminmihu.ktor.monitor.library.domain.model.sizeAsText
import ro.cosminmihu.ktor.monitor.library.domain.model.totalSizeAsText
import ro.cosminmihu.ktor.monitor.library.ui.detail.DetailUiState.Call
import ro.cosminmihu.ktor.monitor.library.ui.detail.DetailUiState.Request
import ro.cosminmihu.ktor.monitor.library.ui.detail.DetailUiState.Response
import kotlin.time.Duration.Companion.seconds

private const val NO_DATA = "-"

class DetailViewModel(
    id: String,
    getCallUseCase: GetCallUseCase,
) : ViewModel() {

    val uiState = getCallUseCase(id)
        .map { call ->
            call ?: return@map DetailUiState()

            DetailUiState(
                summary = DetailUiState.Summary(
                    url = call.url,
                    method = call.method,
                    protocol = call.protocol ?: NO_DATA,
                    requestTime = call.requestDateTimeAsText,
                    responseCode = call.responseCode?.toString() ?: NO_DATA,
                    responseTime = call.responseDateTimeAsText ?: NO_DATA,
                    duration = call.durationAsText ?: NO_DATA,
                    requestSize = call.requestContentLength.sizeAsText(),
                    responseSize = call.responseContentLength?.sizeAsText() ?: NO_DATA,
                    totalSize = call.totalSizeAsText ?: NO_DATA,
                    isLoading = call.isInProgress,
                    isError = call.isError,
                ),
                call = Call(
                    id = call.id,
                    isSecure = call.isSecure,
                    request = Request(
                        method = call.method,
                        host = call.host,
                        pathAndQuery = call.encodedPathAndQuery,
                        requestTime = call.requestTimeAsText,
                        headers = call.requestHeaders,
                        body = DetailUiState.Body(
                            bytes = bodyBytes(call.requestBody),
                            raw = bodyString(call.requestBody),
                            code = bodyCode(call.requestContentType, call.requestBody),
                            image = bodyImage(call.requestContentType, call.requestBody),
                            html = bodyHtml(call.responseContentType, call.requestBody),
                        )
                    ),
                    response = Response(
                        responseCode = call.responseCode?.toString() ?: "",
                        contentType = call.responseContentType?.contentType ?: ContentType.UNKNOWN,
                        duration = call.durationAsText ?: "",
                        size = call.responseContentLength?.sizeAsText() ?: "",
                        error = call.error ?: "",
                        headers = call.responseHeaders ?: mapOf(),
                        body = DetailUiState.Body(
                            bytes = bodyBytes(call.responseBody),
                            raw = bodyString(call.responseBody),
                            code = bodyCode(call.responseContentType, call.responseBody),
                            image = bodyImage(call.responseContentType, call.responseBody),
                            html = bodyHtml(call.responseContentType, call.responseBody),
                        )
                    )
                )
            )
        }
        .flowOn(Dispatchers.Default)
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5.seconds.inWholeMilliseconds),
            DetailUiState()
        )
}

