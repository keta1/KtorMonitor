package ro.cosminmihu.ktor.monitor.library.db

import app.cash.sqldelight.Query
import app.cash.sqldelight.coroutines.asFlow
import kotlinx.coroutines.flow.Flow
import ro.cosminmihu.ktor.monitor.LibraryDatabase
import ro.cosminmihu.ktor.monitor.Call
import ro.cosminmihu.ktor.monitor.SelectCalls
import ro.cosminmihu.ktor.monitor.SelectCallsWithLimit

class LibraryDao(private val database: LibraryDatabase) {

    fun saveRequest(
        id: String,
        method: String,
        url: String,
        requestTime: Long,
        requestSize: Long,
        requestHeaders: Map<String, List<String>>,
        requestContentType: String?,
        requestBody: ByteArray?,
    ) {
        database.callQueries.saveRequest(
            id,
            method,
            url,
            requestTime,
            requestSize,
            requestHeaders,
            requestContentType,
            requestBody
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
        responseCode: Int,
        requestTime: Long,
        responseTime: Long,
        responseContentType: String?,
        responseHeaders: Map<String, List<String>>?,
        protocol: String?,
    ) {
        database.callQueries.saveResponse(
            responseCode.toLong(),
            requestTime,
            responseTime,
            responseContentType,
            responseHeaders,
            protocol,
            id
        )
    }

    fun saveResponseBody(
        id: String,
        responseSize: Long?,
        responseBody: ByteArray?,
    ) {
        database.callQueries.saveResponseBody(
            responseSize,
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
}