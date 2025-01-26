package ro.cosminmihu.ktor.monitor.library.domain

import ro.cosminmihu.ktor.monitor.library.db.LibraryDao

class DeleteCallsUseCase(
    private val dao: LibraryDao,
) {

    operator fun invoke() {
        dao.deleteCalls()
    }
}