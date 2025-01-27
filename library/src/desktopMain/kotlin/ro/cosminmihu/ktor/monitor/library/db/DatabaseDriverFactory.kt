package ro.cosminmihu.ktor.monitor.library.db

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import ro.cosminmihu.ktor.monitor.LibraryDatabase
import java.io.File

actual fun createDatabaseDriver(): SqlDriver {
    val filePath = File(DATABASE_NAME)
    return JdbcSqliteDriver("jdbc:sqlite:$filePath").also {
        LibraryDatabase.Schema.create(it)
    }
}