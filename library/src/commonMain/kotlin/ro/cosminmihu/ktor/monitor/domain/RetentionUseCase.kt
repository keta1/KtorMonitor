package ro.cosminmihu.ktor.monitor.domain

import kotlinx.datetime.Clock
import ro.cosminmihu.ktor.monitor.RetentionPeriod
import ro.cosminmihu.ktor.monitor.db.LibraryDao

internal class RetentionUseCase(
    private val configUseCase: ConfigUseCase,
    private val dao: LibraryDao,
) {
    suspend operator fun invoke() {
        val retentionPeriod = configUseCase.getRetentionPeriod()
        if (retentionPeriod == RetentionPeriod.Forever) return

        val threshold = Clock.System.now().minus(retentionPeriod).toEpochMilliseconds()
        dao.deleteCallsBefore(threshold)
    }
}