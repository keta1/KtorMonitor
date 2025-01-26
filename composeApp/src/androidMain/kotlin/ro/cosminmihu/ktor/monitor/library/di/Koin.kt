package ro.cosminmihu.ktor.monitor.library.di

import android.content.Context
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.dsl.module
import ro.cosminmihu.ktor.monitor.library.domain.ListenByRecentCallsUseCase

/**
 * Init.
 */
object KtorMonitor {

    fun init(context: Context) {
        LibraryKoinContext.init(
            module {
                factory { context.applicationContext }
            }
        )

        // Listen by calls.
        GlobalScope.launch {
            delay(1000) // TODO
            LibraryKoinContext.koin.get<ListenByRecentCallsUseCase>()()
        }
    }
}