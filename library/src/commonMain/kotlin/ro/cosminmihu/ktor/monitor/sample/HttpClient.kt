package ro.cosminmihu.ktor.monitor.sample

import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import ro.cosminmihu.ktor.monitor.library.KtorMonitorLogging
import kotlin.time.Duration.Companion.seconds

fun httpClient() = HttpClient {
    install(HttpTimeout) {
        connectTimeoutMillis = 20.seconds.inWholeMilliseconds
        requestTimeoutMillis = 20.seconds.inWholeMilliseconds
        socketTimeoutMillis = 20.seconds.inWholeMilliseconds
    }
    install(Logging) {
        level = LogLevel.ALL
        logger = Logger.SIMPLE
    }
    install(KtorMonitorLogging) {
//        sanitizeHeader { header -> header == "Vary" }
//        filter { request -> request.url.host.contains("dex") }
//        isActive = false
//        showNotification = false
//        retentionPeriod = RetentionPeriod.OneHour
    }
}