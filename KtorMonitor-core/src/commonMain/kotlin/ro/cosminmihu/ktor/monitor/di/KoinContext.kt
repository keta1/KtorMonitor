package ro.cosminmihu.ktor.monitor.di

import org.koin.core.Koin
import org.koin.core.KoinApplication
import org.koin.core.component.KoinComponent
import org.koin.dsl.koinApplication

internal interface LibraryKoinComponent : KoinComponent {
    override fun getKoin(): Koin = LibraryKoinContext.koin
}

public object LibraryKoinContext {
    public val koinApp: KoinApplication = koinApplication {
        modules(libraryModule())
    }

    internal val koin = koinApp.koin
}
