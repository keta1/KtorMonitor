package ro.cosminmihu.ktor.monitor.di

import org.koin.core.Koin
import org.koin.core.component.KoinComponent
import org.koin.dsl.koinApplication
import org.koin.dsl.module
import ro.cosminmihu.ktor.monitor.api.LoggingConfig
import ro.cosminmihu.ktor.monitor.core.PlatformContext

internal interface LibraryKoinComponent : KoinComponent {

    override fun getKoin(): Koin = LibraryKoinContext.koin
}

internal object LibraryKoinContext {

    internal val koinApp = koinApplication {
        modules(libraryModule())
    }

    internal val koin = koinApp.koin

    internal fun set(platformContext: PlatformContext) {
        val alreadyInjected = koin.getOrNull<PlatformContext>() != null
        if (alreadyInjected) return

        koin.loadModules(modules = listOf(module { single { platformContext } }))
    }

    internal fun set(config: LoggingConfig) {
        this@LibraryKoinContext.applyPlatformContext()

        val alreadyInjected = koin.getOrNull<LoggingConfig>() != null
        if (alreadyInjected) return

        koin.loadModules(modules = listOf(module { factory { config } }))
    }

    internal fun applyPlatformContext() {
        ro.cosminmihu.ktor.monitor.core.applyPlatformContext()
    }
}