package ro.cosminmihu.ktor.monitor.library.db

import app.cash.sqldelight.Query
import app.cash.sqldelight.coroutines.asFlow
import kotlinx.coroutines.flow.Flow
import ro.cosminmihu.ktor.monitor.Call
import ro.cosminmihu.ktor.monitor.LibraryDatabase
import ro.cosminmihu.ktor.monitor.SelectCalls
import ro.cosminmihu.ktor.monitor.SelectCallsWithLimit

class LibraryDao(private val database: LibraryDatabase) {

    fun saveRequest(
        id: String,
        method: String,
        url: String,
        requestTimestamp: Long,
        requestHeaders: Map<String, List<String>>,
        requestContentType: String?,
        requestContentLength: Long,
        requestBody: ByteArray?,
    ) {
        database.callQueries.saveRequest(
            id,
            method,
            url,
            requestTimestamp,
            requestHeaders,
            requestContentType,
            requestContentLength,
            requestBody,
        )
    }

    fun saveRequest(
        id: String,
        error: Throwable,
    ) {
        database.callQueries.saveError(
            error.stackTraceToString(),
            id
        )
    }

    fun saveResponse(
        id: String,
        protocol: String?,
        requestTimestamp: Long,
        responseCode: Int,
        responseTimestamp: Long,
        responseContentType: String?,
        responseHeaders: Map<String, List<String>>?,
    ) {
        database.callQueries.saveResponse(
            protocol,
            requestTimestamp,
            responseCode.toLong(),
            responseTimestamp,
            responseContentType,
            responseHeaders,
            id
        )
    }

    fun saveResponseBody(
        id: String,
        responseContentLength: Long?,
        responseBody: ByteArray?,
    ) {
        database.callQueries.saveResponseBody(
            responseContentLength,
            responseBody,
            id
        )
    }

    fun saveResponse(
        id: String,
        error: Throwable,
    ) {
        database.callQueries.saveError(
            error.stackTraceToString(),
            id
        )
    }

    fun getCalls(): Flow<Query<SelectCalls>> =
        database.callQueries.selectCalls().asFlow()

    fun getCall(id: String): Flow<Query<Call>> =
        database.callQueries.selectCall(id).asFlow()

    fun getCalls(limit: Long): Flow<Query<SelectCallsWithLimit>> =
        database.callQueries.selectCallsWithLimit(limit).asFlow()

    fun deleteCalls() {
        database.callQueries.deleteCalls()
    }

    fun deleteCallsBefore(threshold: Long) {
        database.callQueries.deleteCallsBefore(threshold)
    }
}