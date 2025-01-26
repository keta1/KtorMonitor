package ro.cosminmihu.ktor.monitor.library.db

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import ro.cosminmihu.ktor.monitor.LibraryDatabase

actual fun createDatabaseDriver(): SqlDriver {
    return NativeSqliteDriver(LibraryDatabase.Schema, DATABASE_NAME)
}
