package ro.cosminmihu.ktor.monitor.db

import app.cash.sqldelight.Query
import app.cash.sqldelight.coroutines.asFlow
import kotlinx.coroutines.flow.Flow
import ro.cosminmihu.ktor.monitor.db.sqldelight.Call
import ro.cosminmihu.ktor.monitor.db.sqldelight.LibraryDatabase
import ro.cosminmihu.ktor.monitor.db.sqldelight.SelectCalls
import ro.cosminmihu.ktor.monitor.db.sqldelight.SelectCallsWithLimit

public class LibraryDao(private val database: LibraryDatabase) {
    public fun saveRequest(
        id: String,
        method: String,
        url: String,
        requestTimestamp: Long,
        requestHeaders: Map<String, List<String>>,
        requestContentType: String?,
        requestContentLength: Long,
        requestBody: ByteArray?,
        isRequestBodyTruncated: Boolean,
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
            isRequestBodyTruncated,
        )
    }

    public fun saveRequest(
        id: String,
        error: Throwable,
    ) {
        database.callQueries.saveError(
            error.stackTraceToString(),
            id
        )
    }

    public fun saveResponse(
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

    public fun saveResponseBody(
        id: String,
        responseContentLength: Long?,
        responseBody: ByteArray?,
        isResponseBodyTruncated: Boolean,
    ) {
        database.callQueries.saveResponseBody(
            responseContentLength,
            responseBody,
            isResponseBodyTruncated,
            id
        )
    }

    public fun saveResponse(
        id: String,
        error: Throwable,
    ) {
        database.callQueries.saveError(
            error.stackTraceToString(),
            id
        )
    }

    public fun getCalls(): Flow<Query<SelectCalls>> =
        database.callQueries.selectCalls().asFlow()

    public fun getCall(id: String): Flow<Query<Call>> =
        database.callQueries.selectCall(id).asFlow()

    public fun getCalls(limit: Long): Flow<Query<SelectCallsWithLimit>> =
        database.callQueries.selectCallsWithLimit(limit).asFlow()

    public fun deleteCalls() {
        database.callQueries.deleteCalls()
    }

    public fun deleteCallsBefore(threshold: Long) {
        database.callQueries.deleteCallsBefore(threshold)
    }
}
