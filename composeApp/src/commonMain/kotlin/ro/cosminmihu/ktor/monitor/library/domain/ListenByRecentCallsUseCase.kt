package ro.cosminmihu.ktor.monitor.library.domain

import app.cash.sqldelight.Query
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import ro.cosminmihu.ktor.monitor.SelectCallsWithLimit
import ro.cosminmihu.ktor.monitor.library.api.LoggingConfig
import ro.cosminmihu.ktor.monitor.library.db.LibraryDao
import ro.cosminmihu.ktor.monitor.library.domain.model.encodedPathAndQuery
import ro.cosminmihu.ktor.monitor.library.domain.model.isError
import ro.cosminmihu.ktor.monitor.library.domain.model.isInProgress
import ro.cosminmihu.ktor.monitor.library.ui.notification.NotificationManager

class ListenByRecentCallsUseCase(
    private val dao: LibraryDao,
    private val config: LoggingConfig,
    private val coroutineScope: CoroutineScope,
    private val notificationManager: NotificationManager,
    private val retentionUseCase: RetentionUseCase,
) {

    operator fun invoke() {
        coroutineScope.launch {
            dao.getCalls(5)
                .map(Query<SelectCallsWithLimit>::executeAsList)
                .map {
                    it.map {
                        buildString {
                            append(
                                when {
                                    it.isInProgress -> "⏳"
                                    it.isError -> "❌"
                                    else -> it.responseCode
                                }
                            )
                            append(" ")
                            append(it.method)
                            append(" ")
                            append(it.encodedPathAndQuery)
                        }
                    }
                }
                .distinctUntilChanged()
                .collect {
                    // Show notification
                    if (config.showNotification) {
                        notificationManager.notify(it)
                    }

                    // Delete old calls.
                    retentionUseCase()
                }
        }
    }
}