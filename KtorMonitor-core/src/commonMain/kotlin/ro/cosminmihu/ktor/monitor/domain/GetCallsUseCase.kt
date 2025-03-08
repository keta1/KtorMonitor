package ro.cosminmihu.ktor.monitor.domain

import app.cash.sqldelight.Query
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ro.cosminmihu.ktor.monitor.db.LibraryDao
import ro.cosminmihu.ktor.monitor.db.sqldelight.SelectCalls

public class GetCallsUseCase(
    private val dao: LibraryDao,
) {
    public operator fun invoke(): Flow<List<SelectCalls>> = dao.getCalls()
        .map(Query<SelectCalls>::executeAsList)
}
