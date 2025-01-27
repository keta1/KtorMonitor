package ro.cosminmihu.ktor.monitor.library.domain

import app.cash.sqldelight.Query
import kotlinx.coroutines.flow.map
import ro.cosminmihu.ktor.monitor.library.db.LibraryDao
import ro.cosminmihu.ktor.monitor.SelectCalls

class GetCallsUseCase(
    private val dao: LibraryDao,
) {

    operator fun invoke() = dao.getCalls()
        .map(Query<SelectCalls>::executeAsList)
}