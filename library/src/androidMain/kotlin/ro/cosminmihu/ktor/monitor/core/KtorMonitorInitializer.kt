package ro.cosminmihu.ktor.monitor.core

import android.content.Context
import androidx.startup.Initializer
import org.koin.dsl.module
import ro.cosminmihu.ktor.monitor.di.LibraryKoinContext

/**
 * Content provider used to initialize the [android.content.Context].
 */
internal class KtorMonitorInitializer : Initializer<Unit> {
    override fun create(context: Context) {
        LibraryKoinContext.koin.loadModules(modules = listOf(module { single { context } }))
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> = mutableListOf()
}
