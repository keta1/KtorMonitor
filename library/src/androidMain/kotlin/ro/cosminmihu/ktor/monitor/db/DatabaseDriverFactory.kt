package ro.cosminmihu.ktor.monitor.db

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import ro.cosminmihu.ktor.monitor.core.PlatformContext
import ro.cosminmihu.ktor.monitor.db.sqldelight.LibraryDatabase

internal actual fun createDatabaseDriver(platformContext: PlatformContext): SqlDriver {
    return AndroidSqliteDriver(
        schema = LibraryDatabase.Schema,
        context = platformContext,
        name = DATABASE_NAME
    )
}
