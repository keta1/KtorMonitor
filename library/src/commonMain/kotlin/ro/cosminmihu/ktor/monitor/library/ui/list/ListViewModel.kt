package ro.cosminmihu.ktor.monitor.library.ui.list

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
import ro.cosminmihu.ktor.monitor.library.domain.DeleteCallsUseCase
import ro.cosminmihu.ktor.monitor.library.domain.GetCallsUseCase
import ro.cosminmihu.ktor.monitor.library.domain.model.ContentType
import ro.cosminmihu.ktor.monitor.library.domain.model.contentType
import ro.cosminmihu.ktor.monitor.library.domain.model.durationAsText
import ro.cosminmihu.ktor.monitor.library.domain.model.encodedPathAndQuery
import ro.cosminmihu.ktor.monitor.library.domain.model.host
import ro.cosminmihu.ktor.monitor.library.domain.model.isSecure
import ro.cosminmihu.ktor.monitor.library.domain.model.requestTimeAsText
import ro.cosminmihu.ktor.monitor.library.domain.model.sizeAsText
import ro.cosminmihu.ktor.monitor.SelectCalls
import kotlin.time.Duration.Companion.seconds

@OptIn(FlowPreview::class)
class ListViewModel(
    getCallsUseCase: GetCallsUseCase,
    private val deleteCallsUseCase: DeleteCallsUseCase,
) : ViewModel() {

    private val _searchQuery = MutableStateFlow("")
    private val searchQuery = _searchQuery.debounce(0.5.seconds)
    private val calls = getCallsUseCase()

    val uiState = combine(
        searchQuery,
        calls
    ) { query, calls ->
        query to when {
            query.isBlank() -> calls
            else -> calls.filter { it.url.contains(query.trim(), ignoreCase = true) }
        }
    }
        .flowOn(Dispatchers.Default)
        .map { (query, it) -> buildUiState(query, it) }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5.seconds.inWholeMilliseconds),
            ListUiState()
        )

    private fun buildUiState(
        query: String,
        calls: List<SelectCalls>,
    ): ListUiState = ListUiState(
        searchQuery = query,
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
                    error = it.error ?: ""
                )
            )
        }
    )

    fun deleteCalls() {
        deleteCallsUseCase()
    }

    fun setSearchQuery(query: String) {
        this@ListViewModel._searchQuery.update { query }
    }

    fun clearSearchQuery() {
        this@ListViewModel._searchQuery.update { "" }
    }
}
