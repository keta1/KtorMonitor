package ro.cosminmihu.ktor.monitor.domain.model

import kotlin.time.Duration

internal data class Config(
    val isActive: Boolean,
    val showNotification: Boolean,
    val retentionPeriod: Duration,
)