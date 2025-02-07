package ro.cosminmihu.ktor.monitor.domain

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import ro.cosminmihu.ktor.monitor.api.LoggingConfig
import ro.cosminmihu.ktor.monitor.domain.model.Setup

internal class SetupUseCase {

    private val setup = MutableStateFlow<Setup>(Setup())

    internal val isSetupDone = setup.map { it.loggingConfig != null }
    internal val showNotification = setup.map { it.loggingConfig?.showNotification == true }
    internal val retentionPeriod = setup.map { it.loggingConfig?.retentionPeriod }

    internal fun setLoggingConfig(config: LoggingConfig) {
        setup.update { it.copy(loggingConfig = config) }
    }
}