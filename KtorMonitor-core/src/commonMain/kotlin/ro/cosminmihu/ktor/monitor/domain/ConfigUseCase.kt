package ro.cosminmihu.ktor.monitor.domain

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import ro.cosminmihu.ktor.monitor.domain.model.Config

public class ConfigUseCase {
    private val config = MutableStateFlow(Config.Disabled)
    public val isActive: Flow<Boolean> = config.map { it.isActive }

    internal fun setConfig(config: Config) {
        this@ConfigUseCase.config.update { config }
    }

    internal suspend fun getRetentionPeriod() = config.first().retentionPeriod

    public suspend fun isShowNotification(): Boolean = config.first().showNotification

    internal fun getMaxContentLength() = config.value.maxContentLength
}
