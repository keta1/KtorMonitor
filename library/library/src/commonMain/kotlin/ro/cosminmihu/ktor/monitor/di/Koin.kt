package ro.cosminmihu.ktor.monitor.di

import org.koin.core.Koin
import org.koin.core.component.KoinComponent
import org.koin.core.parameter.ParametersHolder
import org.koin.core.qualifier.Qualifier
import org.koin.dsl.koinApplication
import org.koin.dsl.module
import ro.cosminmihu.ktor.monitor.KtorMonitorLoggingConfig
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

    internal fun set(config: KtorMonitorLoggingConfig) {
        this@LibraryKoinContext.applyPlatformContext()

        val alreadyInjected = koin.getOrNull<KtorMonitorLoggingConfig>() != null
        if (alreadyInjected) return

        koin.loadModules(modules = listOf(module { factory { config } }))
    }

    internal fun applyPlatformContext() {
        ro.cosminmihu.ktor.monitor.core.applyPlatformContext()
    }
}

internal inline fun <reified T : Any> LibraryKoinContext.get(
    qualifier: Qualifier? = null,
    noinline parameters: (() -> ParametersHolder)? = null,
) = koin.get<T>(qualifier, parameters)


internal inline fun <reified T : Any> LibraryKoinContext.inject(
    qualifier: Qualifier? = null,
    mode: LazyThreadSafetyMode = LazyThreadSafetyMode.SYNCHRONIZED,
    noinline parameters: (() -> ParametersHolder)? = null,
) = koin.inject<T>(qualifier, mode, parameters)
