package ro.cosminmihu.ktor.monitor.domain.model

import ro.cosminmihu.ktor.monitor.api.LoggingConfig
import ro.cosminmihu.ktor.monitor.core.PlatformContext

internal data class Setup(
    val loggingConfig: LoggingConfig? = null,
    val platformContext: PlatformContext? = null,
)
