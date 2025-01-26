package ro.cosminmihu.ktor.monitor.library.di

import org.koin.core.Koin
import org.koin.core.component.KoinComponent
import org.koin.core.module.Module
import org.koin.core.parameter.ParametersHolder
import org.koin.core.qualifier.Qualifier
import org.koin.dsl.koinApplication

internal interface LibraryKoinComponent : KoinComponent {

    override fun getKoin(): Koin = LibraryKoinContext.koin
}

internal object LibraryKoinContext {

    internal val koinApp = koinApplication {
        modules(libraryModule())
    }

    internal val koin = koinApp.koin

    internal fun init(module: Module? = null) {
        koin
        if (module != null) {
            koin.loadModules(listOf(module))
        }
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
