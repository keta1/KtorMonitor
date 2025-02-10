package ro.cosminmihu.ktor.monitor.sample

import io.ktor.client.HttpClient
import ro.cosminmihu.ktor.monitor.ContentLength
import ro.cosminmihu.ktor.monitor.KtorMonitorLogging
import ro.cosminmihu.ktor.monitor.RetentionPeriod

internal fun httpClient() = HttpClient {
    install(KtorMonitorLogging) {
        sanitizeHeader { header -> header == "Authorization" }
        filter { request -> !request.url.host.contains("cosminmihu.ro") }
        showNotification = true
        retentionPeriod = RetentionPeriod.OneHour
        maxContentLength = ContentLength.Default
    }
}