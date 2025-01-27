package ro.cosminmihu.ktor.monitor.library.domain

import kotlinx.datetime.Clock
import ro.cosminmihu.ktor.monitor.library.api.LoggingConfig
import ro.cosminmihu.ktor.monitor.library.api.RetentionPeriod
import ro.cosminmihu.ktor.monitor.library.db.LibraryDao

class RetentionUseCase(
    private val config: LoggingConfig,
    private val dao: LibraryDao,
) {
    operator fun invoke() {
        val retentionPeriod = config.retentionPeriod
        if (retentionPeriod == RetentionPeriod.Forever) return

        val threshold = Clock.System.now().minus(retentionPeriod).toEpochMilliseconds()
        dao.deleteCallsBefore(threshold)
    }
}