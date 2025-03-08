package ro.cosminmihu.ktor.monitor.domain.model

import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format
import kotlinx.datetime.format.DateTimeComponents
import kotlinx.datetime.format.DayOfWeekNames
import kotlinx.datetime.format.MonthNames
import kotlinx.datetime.format.char
import kotlinx.datetime.format.optional
import kotlinx.datetime.toLocalDateTime
import kotlin.math.log
import kotlin.math.pow
import kotlin.math.round

internal fun Long.formatTime() = Instant.fromEpochMilliseconds(this)
    .toLocalDateTime(TimeZone.currentSystemDefault())
    .format(
        LocalDateTime.Format {
            hour(); char(':'); minute(); char(':'); second()
            optional { char('.'); secondFraction(maxLength = 1) }
        }
    )

internal fun Long.formatDateTimeTime() = Instant.fromEpochMilliseconds(this)
    .format(
        DateTimeComponents.Format {
            dayOfWeek(DayOfWeekNames.ENGLISH_ABBREVIATED)
            char(',')
            char(' ')
            year(); char(' '); monthName(MonthNames.ENGLISH_ABBREVIATED); char(' '); dayOfMonth()
            char(' ')
            hour(); char(':'); minute(); char(':'); second()
            optional { char('.'); secondFraction() }
            char(' ')
        }
    )

internal fun Long.sizeAsText(): String {
    if (this < 1024) return "$this B"
    val units = arrayOf("B", "KB", "MB", "GB", "TB", "PB", "EB")
    val exp = (log(this.toDouble(), 1024.0)).toInt()
    val size = this / 1024.0.pow(exp.toDouble())
    val roundedSize = (round(size * 100) / 100) // Round to 2 decimal places
    return "$roundedSize ${units[exp]}"
}
