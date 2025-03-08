package ro.cosminmihu.ktor.monitor.domain

import ro.cosminmihu.ktor.monitor.db.LibraryDao
import ro.cosminmihu.ktor.monitor.notification.NotificationManager

public class DeleteCallsUseCase(
    private val dao: LibraryDao,
    private val notificationManager: NotificationManager,
) {
    public suspend operator fun invoke() {
        dao.deleteCalls()
        notificationManager.clear()
    }
}
