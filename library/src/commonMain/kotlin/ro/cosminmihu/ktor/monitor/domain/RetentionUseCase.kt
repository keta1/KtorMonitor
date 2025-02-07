package ro.cosminmihu.ktor.monitor.domain

import kotlinx.datetime.Clock
import ro.cosminmihu.ktor.monitor.RetentionPeriod
import ro.cosminmihu.ktor.monitor.api.LoggingConfig
import ro.cosminmihu.ktor.monitor.db.LibraryDao

internal class RetentionUseCase(
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