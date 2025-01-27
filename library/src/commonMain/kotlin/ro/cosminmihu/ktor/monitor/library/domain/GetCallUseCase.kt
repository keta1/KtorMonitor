package ro.cosminmihu.ktor.monitor.library.domain

import kotlinx.coroutines.flow.map
import ro.cosminmihu.ktor.monitor.library.db.LibraryDao

class GetCallUseCase(
    private val dao: LibraryDao,
) {

    operator fun invoke(id: String) = dao.getCall(id)
        .map { it.executeAsOneOrNull() }
}