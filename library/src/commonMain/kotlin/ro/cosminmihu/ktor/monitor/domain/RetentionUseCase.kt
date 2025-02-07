package ro.cosminmihu.ktor.monitor.domain

import kotlinx.coroutines.flow.firstOrNull
import kotlinx.datetime.Clock
import ro.cosminmihu.ktor.monitor.RetentionPeriod
import ro.cosminmihu.ktor.monitor.db.LibraryDao

internal class RetentionUseCase(
    private val setupUseCase: ConfigUseCase,
    private val dao: LibraryDao,
) {
    suspend operator fun invoke() {
        val retentionPeriod = setupUseCase.retentionPeriod.firstOrNull() ?: return
        if (retentionPeriod == RetentionPeriod.Forever) return

        val threshold = Clock.System.now().minus(retentionPeriod).toEpochMilliseconds()
        dao.deleteCallsBefore(threshold)
    }
}