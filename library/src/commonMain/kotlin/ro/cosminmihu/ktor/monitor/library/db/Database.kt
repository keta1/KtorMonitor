package ro.cosminmihu.ktor.monitor.library.db

import app.cash.sqldelight.db.SqlDriver
import ro.cosminmihu.ktor.monitor.LibraryDatabase
import ro.cosminmihu.ktor.monitor.Call

const val DATABASE_NAME = "ktor_monitor.db"

expect fun createDatabaseDriver(): SqlDriver

fun createDatabase(driver: SqlDriver): LibraryDatabase {
    return LibraryDatabase(
        driver = driver,
        callAdapter = Call.Adapter(
            requestHeadersAdapter = HeadersAdapter,
            responseHeadersAdapter = HeadersAdapter,
        )
    )
}