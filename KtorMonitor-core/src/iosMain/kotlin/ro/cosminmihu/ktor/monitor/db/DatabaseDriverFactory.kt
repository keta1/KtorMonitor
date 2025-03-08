package ro.cosminmihu.ktor.monitor.db

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import ro.cosminmihu.ktor.monitor.db.sqldelight.LibraryDatabase

internal actual fun createDatabaseDriver(): SqlDriver {
    return NativeSqliteDriver(LibraryDatabase.Schema, DATABASE_NAME)
}
