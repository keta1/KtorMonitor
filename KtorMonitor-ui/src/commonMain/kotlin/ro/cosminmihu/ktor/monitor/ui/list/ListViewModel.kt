package ro.cosminmihu.ktor.monitor.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ro.cosminmihu.ktor.monitor.db.sqldelight.SelectCalls
import ro.cosminmihu.ktor.monitor.domain.ConfigUseCase
import ro.cosminmihu.ktor.monitor.domain.DeleteCallsUseCase
import ro.cosminmihu.ktor.monitor.ui.model.ContentType
import ro.cosminmihu.ktor.monitor.ui.model.contentType
import ro.cosminmihu.ktor.monitor.domain.GetCallsUseCase
import ro.cosminmihu.ktor.monitor.ui.model.durationAsText
import ro.cosminmihu.ktor.monitor.ui.model.encodedPathAndQuery
import ro.cosminmihu.ktor.monitor.ui.model.host
import ro.cosminmihu.ktor.monitor.ui.model.isSecure
import ro.cosminmihu.ktor.monitor.ui.model.requestTimeAsText
import ro.cosminmihu.ktor.monitor.ui.model.sizeAsText
import kotlin.time.Duration.Companion.seconds

@OptIn(FlowPreview::class)
internal class ListViewModel(
    configUseCase: ConfigUseCase,
    getCallsUseCase: GetCallsUseCase,
    private val deleteCallsUseCase: DeleteCallsUseCase,
) : ViewModel() {

    private val _searchQuery = MutableStateFlow("")
    private val searchQuery = _searchQuery.debounce(0.2.seconds)
    private val calls = getCallsUseCase()

    val uiState = combine(
        searchQuery,
        calls,
    ) { query, calls ->
        query to when {
            query.isBlank() -> calls
            else -> calls.filter { it.url.contains(query.trim(), ignoreCase = true) }
        }
    }
        .flowOn(Dispatchers.Default)
        .map { (query, calls) -> buildUiState(query, calls, configUseCase.isShowNotification()) }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5.seconds.inWholeMilliseconds),
            ListUiState()
        )

    private fun buildUiState(
        query: String,
        calls: List<SelectCalls>,
        showNotification: Boolean,
    ): ListUiState = ListUiState(
        searchQuery = query,
        showNotification = showNotification,
        calls = calls.map {
            ListUiState.Call(
                id = it.id,
                isSecure = it.isSecure,
                request = ListUiState.Request(
                    method = it.method,
                    host = it.host,
                    pathAndQuery = it.encodedPathAndQuery,
                    requestTime = it.requestTimeAsText,
                ),
                response = ListUiState.Response(
                    responseCode = it.responseCode?.toString() ?: "",
                    contentType = it.responseContentType?.contentType ?: ContentType.UNKNOWN,
                    duration = it.durationAsText ?: "",
                    size = it.responseContentLength?.sizeAsText() ?: "",
                    error = it.error ?: "",
                )
            )
        }
    )

    fun deleteCalls() {
        viewModelScope.launch {
            deleteCallsUseCase()
        }
    }

    fun setSearchQuery(query: String) {
        this@ListViewModel._searchQuery.update { query }
    }

    fun clearSearchQuery() {
        this@ListViewModel._searchQuery.update { "" }
    }
}
