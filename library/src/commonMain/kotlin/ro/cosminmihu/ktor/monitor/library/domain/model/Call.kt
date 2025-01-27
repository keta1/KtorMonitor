package ro.cosminmihu.ktor.monitor.library.domain.model

import io.ktor.http.URLBuilder
import io.ktor.http.isSecure
import ro.cosminmihu.ktor.monitor.Call
import ro.cosminmihu.ktor.monitor.SelectCalls
import ro.cosminmihu.ktor.monitor.SelectCallsWithLimit
import kotlin.time.Duration.Companion.milliseconds

val SelectCallsWithLimit.isInProgress
    get() = responseCode == null && error == null

val SelectCallsWithLimit.isError
    get() = !error.isNullOrBlank()

val SelectCallsWithLimit.encodedPathAndQuery
    get() = URLBuilder(url).build().encodedPathAndQuery


val SelectCalls.durationAsText
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

val SelectCalls.requestTimeAsText
    get() = requestTimestamp.formatTime()

val SelectCalls.isSecure
    get() = URLBuilder(url).build().protocol.isSecure()

val SelectCalls.host
    get() = URLBuilder(url).build().host

val SelectCalls.encodedPathAndQuery
    get() = URLBuilder(url).build().encodedPathAndQuery


val Call.isInProgress
    get() = responseCode == null && error == null

val Call.isError
    get() = !error.isNullOrBlank()

val Call.requestTimeAsText
    get() = requestTimestamp.formatTime()

val Call.responseTimeAsText
    get() = responseTimestamp?.formatTime()

val Call.responseDateTimeAsText
    get() = responseTimestamp?.formatDateTimeTime()

val Call.requestDateTimeAsText
    get() = requestTimestamp.formatDateTimeTime()

val Call.isSecure
    get() = URLBuilder(url).build().protocol.isSecure()

val Call.host
    get() = URLBuilder(url).build().host

val Call.encodedPathAndQuery
    get() = URLBuilder(url).build().encodedPathAndQuery

val Call.durationAsText
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

val Call.totalSizeAsText: String?
    get() {
        responseContentLength ?: return null
        return (requestContentLength + responseContentLength).sizeAsText()
    }

