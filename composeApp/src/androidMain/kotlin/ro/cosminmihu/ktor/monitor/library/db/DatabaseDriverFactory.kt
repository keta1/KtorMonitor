package ro.cosminmihu.ktor.monitor.library.db

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import org.koin.core.context.GlobalContext
import ro.cosminmihu.ktor.monitor.LibraryDatabase

actual fun createDatabaseDriver(): SqlDriver {
    val context = GlobalContext.get().get<Context>()
    return AndroidSqliteDriver(
        schema = LibraryDatabase.Schema,
        context = context,
        name = DATABASE_NAME
    )
}
