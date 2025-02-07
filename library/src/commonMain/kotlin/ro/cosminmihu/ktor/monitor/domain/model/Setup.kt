package ro.cosminmihu.ktor.monitor.domain.model

import ro.cosminmihu.ktor.monitor.api.LoggingConfig

internal data class Setup(
    val loggingConfig: LoggingConfig? = null,
)
