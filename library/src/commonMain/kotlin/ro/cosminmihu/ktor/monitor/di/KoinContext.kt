package ro.cosminmihu.ktor.monitor.di

import org.koin.core.Koin
import org.koin.core.component.KoinComponent
import org.koin.dsl.koinApplication
import org.koin.dsl.module
import ro.cosminmihu.ktor.monitor.api.LoggingConfig
import ro.cosminmihu.ktor.monitor.core.PlatformContext
import ro.cosminmihu.ktor.monitor.core.applyPlatformContext
import ro.cosminmihu.ktor.monitor.domain.SetupUseCase

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

        koin.get<SetupUseCase>().setPlatformContext(platformContext)
    }
}