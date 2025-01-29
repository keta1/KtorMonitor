package ro.cosminmihu.ktor.monitor.db

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import ro.cosminmihu.ktor.monitor.core.PlatformContext
import ro.cosminmihu.ktor.monitor.db.sqldelight.LibraryDatabase
import java.io.File

internal actual fun createDatabaseDriver(platformContext: PlatformContext): SqlDriver {
    val filePath = File(DATABASE_NAME)
    return JdbcSqliteDriver("jdbc:sqlite:$filePath").also { // TODO use java temp dir
        LibraryDatabase.Schema.create(it)
    }
}