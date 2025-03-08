package ro.cosminmihu.ktor.monitor.ui.di

import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import ro.cosminmihu.ktor.monitor.ui.detail.DetailViewModel
import ro.cosminmihu.ktor.monitor.ui.list.ListViewModel
import ro.cosminmihu.ktor.monitor.ui.main.MainViewModel

internal val viewModelModule = module {
    viewModelOf(::MainViewModel)
    viewModelOf(::ListViewModel)
    viewModelOf(::DetailViewModel)
}
