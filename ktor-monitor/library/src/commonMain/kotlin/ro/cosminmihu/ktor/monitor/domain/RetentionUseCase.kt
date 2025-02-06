package ro.cosminmihu.ktor.monitor.domain

import kotlinx.datetime.Clock
import ro.cosminmihu.ktor.monitor.KtorMonitorConfig.RetentionPeriod
import ro.cosminmihu.ktor.monitor.core.LibraryConfig
import ro.cosminmihu.ktor.monitor.db.LibraryDao

internal class RetentionUseCase(
    private val config: LibraryConfig,
    private val dao: LibraryDao,
) {
    operator fun invoke() {
        val retentionPeriod = config.retentionPeriod
        if (retentionPeriod == RetentionPeriod.Forever) return

        val threshold = Clock.System.now().minus(retentionPeriod).toEpochMilliseconds()
        dao.deleteCallsBefore(threshold)
    }
}