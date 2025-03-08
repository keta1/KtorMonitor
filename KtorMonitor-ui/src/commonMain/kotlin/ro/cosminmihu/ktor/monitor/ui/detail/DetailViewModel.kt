package ro.cosminmihu.ktor.monitor.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import ro.cosminmihu.ktor.monitor.domain.GetCallUseCase
import ro.cosminmihu.ktor.monitor.ui.detail.DetailUiState.Call
import ro.cosminmihu.ktor.monitor.ui.detail.DetailUiState.Request
import ro.cosminmihu.ktor.monitor.ui.detail.DetailUiState.Response
import ro.cosminmihu.ktor.monitor.ui.formater.bodyBytes
import ro.cosminmihu.ktor.monitor.ui.formater.bodyCode
import ro.cosminmihu.ktor.monitor.ui.formater.bodyHtml
import ro.cosminmihu.ktor.monitor.ui.formater.bodyImage
import ro.cosminmihu.ktor.monitor.ui.formater.bodyString
import ro.cosminmihu.ktor.monitor.ui.model.ContentType
import ro.cosminmihu.ktor.monitor.ui.model.contentType
import ro.cosminmihu.ktor.monitor.ui.model.durationAsText
import ro.cosminmihu.ktor.monitor.ui.model.encodedPathAndQuery
import ro.cosminmihu.ktor.monitor.ui.model.host
import ro.cosminmihu.ktor.monitor.ui.model.isError
import ro.cosminmihu.ktor.monitor.ui.model.isInProgress
import ro.cosminmihu.ktor.monitor.ui.model.isRedirect
import ro.cosminmihu.ktor.monitor.ui.model.isSecure
import ro.cosminmihu.ktor.monitor.ui.model.requestDateTimeAsText
import ro.cosminmihu.ktor.monitor.ui.model.requestTimeAsText
import ro.cosminmihu.ktor.monitor.ui.model.responseDateTimeAsText
import ro.cosminmihu.ktor.monitor.ui.model.sizeAsText
import ro.cosminmihu.ktor.monitor.ui.model.totalSizeAsText
import kotlin.time.Duration.Companion.seconds

private const val NO_DATA = "-"

internal class DetailViewModel(
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
                    isRedirect = call.isRedirect,
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
                            code = checkBodyTruncated(call.isRequestBodyTruncated) {
                                bodyCode(call.requestContentType, call.requestBody)
                            },
                            image = checkBodyTruncated(call.isRequestBodyTruncated) {
                                bodyImage(call.requestContentType, call.requestBody)
                            },
                            html = checkBodyTruncated(call.isRequestBodyTruncated) {
                                bodyHtml(call.responseContentType, call.requestBody)
                            },
                            isTrimmed = call.isRequestBodyTruncated == true
                        ),
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
                            code = checkBodyTruncated(call.isResponseBodyTruncated) {
                                bodyCode(call.responseContentType, call.responseBody)
                            },
                            image = checkBodyTruncated(call.isResponseBodyTruncated) {
                                bodyImage(call.responseContentType, call.responseBody)
                            },
                            html = checkBodyTruncated(call.isResponseBodyTruncated) {
                                bodyHtml(call.responseContentType, call.responseBody)
                            },
                            isTrimmed = call.isResponseBodyTruncated == true
                        ),
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


private fun <T> checkBodyTruncated(isTruncated: Boolean?, value: () -> T): T? {
    return when (isTruncated) {
        true -> null
        else -> value()
    }
}
