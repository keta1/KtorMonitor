package ro.cosminmihu.ktor.monitor.library.di

import android.content.Context
import org.koin.dsl.module

/**
 * Init.
 */
object KtorMonitor {

    fun init(context: Context) {
        LibraryKoinContext.init(
            module(createdAtStart = true) {
                factory { context.applicationContext }
            }
        )
    }
}