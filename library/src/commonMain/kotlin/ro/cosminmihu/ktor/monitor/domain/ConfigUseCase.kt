package ro.cosminmihu.ktor.monitor.domain

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import ro.cosminmihu.ktor.monitor.domain.model.Config

internal class ConfigUseCase {

    private val config = MutableStateFlow<Config?>(null)

    internal val isSetupDone = config.map { it != null }
    internal val showNotification = config.map { it?.showNotification == true }
    internal val retentionPeriod = config.map { it?.retentionPeriod }

    internal fun setConfig(config: Config) {
        this@ConfigUseCase.config.update { config }
    }
}