package ro.cosminmihu.ktor.monitor.ui.model

import io.ktor.http.URLBuilder
import io.ktor.http.isSecure
import ro.cosminmihu.ktor.monitor.db.sqldelight.Call
import kotlin.time.Duration.Companion.milliseconds

internal val Call.isInProgress
    get() = responseCode == null && error == null

internal val Call.isRedirect
    get() = responseCode?.toInt() in 300 until 400

internal val Call.isError
    get() = !error.isNullOrBlank()

internal val Call.requestTimeAsText
    get() = requestTimestamp.formatTime()

internal val Call.responseTimeAsText
    get() = responseTimestamp?.formatTime()

internal val Call.responseDateTimeAsText
    get() = responseTimestamp?.formatDateTimeTime()

internal val Call.requestDateTimeAsText
    get() = requestTimestamp.formatDateTimeTime()

internal val Call.isSecure
    get() = URLBuilder(url).build().protocol.isSecure()

internal val Call.host
    get() = URLBuilder(url).build().host

internal val Call.encodedPathAndQuery
    get() = URLBuilder(url).build().encodedPathAndQuery

internal val Call.durationAsText
    get() = responseTimestamp?.minus(requestTimestamp)?.milliseconds?.toComponents { hours, minutes, seconds, nanoseconds ->
        val milliseconds = nanoseconds / 1_000_000
        when {
            hours > 0 -> "$hours:$minutes:$seconds $milliseconds ms"
            minutes > 0 -> "$minutes:$seconds $milliseconds ms"
            seconds > 0 -> "$seconds $milliseconds ms"
            milliseconds > 0 -> "$milliseconds ms"
            else -> null
        }
    }

internal val Call.totalSizeAsText: String?
    get() {
        val responseContentLength = responseContentLength
        responseContentLength ?: return null
        return (requestContentLength + responseContentLength).sizeAsText()
    }
