package ro.cosminmihu.ktor.monitor.domain

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import ro.cosminmihu.ktor.monitor.db.LibraryDao
import ro.cosminmihu.ktor.monitor.db.sqldelight.Call

public class GetCallUseCase(
    private val dao: LibraryDao,
) {
    public operator fun invoke(id: String): Flow<Call?> = dao.getCall(id)
        .map { it.executeAsOneOrNull() }
        .distinctUntilChanged { old, new ->
            old?.id == new?.id &&
                old?.method == new?.method &&
                old?.url == new?.url &&
                old?.protocol == new?.protocol &&
                old?.requestTimestamp == new?.requestTimestamp &&
                old?.requestHeaders == new?.requestHeaders &&
                old?.requestContentType == new?.requestContentType &&
                old?.requestContentLength == new?.requestContentLength &&
                old?.responseCode == new?.responseCode &&
                old?.responseTimestamp == new?.responseTimestamp &&
                old?.responseContentType == new?.responseContentType &&
                old?.responseHeaders == new?.responseHeaders &&
                old?.responseContentLength == new?.responseContentLength &&
                old?.error == new?.error
        }
}
