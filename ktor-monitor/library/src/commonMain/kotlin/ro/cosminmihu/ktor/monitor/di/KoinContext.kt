package ro.cosminmihu.ktor.monitor.di

import org.koin.core.Koin
import org.koin.core.component.KoinComponent
import org.koin.dsl.koinApplication
import org.koin.dsl.module
import ro.cosminmihu.ktor.monitor.core.LibraryConfig
import ro.cosminmihu.ktor.monitor.core.PlatformContext
import ro.cosminmihu.ktor.monitor.core.applyPlatformContext

internal interface LibraryKoinComponent : KoinComponent {

    override fun getKoin(): Koin = LibraryKoinContext.koin
}

internal object LibraryKoinContext {

    internal val koinApp = koinApplication {
        modules(libraryModule())
    }

    internal val koin = koinApp.koin

    internal fun setPlatformContext(platformContext: PlatformContext) {
        val alreadyInjected = koin.getOrNull<PlatformContext>() != null
        if (alreadyInjected) return

        koin.loadModules(modules = listOf(module { single { platformContext } }))
    }

    internal fun setLibraryConfig(config: LibraryConfig) {
        applyPlatformContext()

        val alreadyInjected = koin.getOrNull<LibraryConfig>() != null
        if (alreadyInjected) return

        koin.loadModules(modules = listOf(module { factory { config } }))
    }

    internal fun getLibraryConfig(): LibraryConfig {
        val config = koin.getOrNull<LibraryConfig>()
        checkNotNull(config) { "KtorMonitorConfig is not initialized." }
        return config
    }
}