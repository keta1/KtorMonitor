package ro.cosminmihu.ktor.monitor.library.domain

import ro.cosminmihu.ktor.monitor.library.db.LibraryDao
import ro.cosminmihu.ktor.monitor.library.ui.notification.NotificationManager

class DeleteCallsUseCase(
    private val dao: LibraryDao,
    private val notificationManager: NotificationManager,
) {

    operator fun invoke() {
        dao.deleteCalls()
        notificationManager.clear()
    }
}