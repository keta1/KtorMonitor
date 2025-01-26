package ro.cosminmihu.ktor.monitor.library.di

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import ro.cosminmihu.ktor.monitor.library.db.LibraryDao
import ro.cosminmihu.ktor.monitor.library.db.createDatabase
import ro.cosminmihu.ktor.monitor.library.db.createDatabaseDriver
import ro.cosminmihu.ktor.monitor.library.domain.DeleteCallsUseCase
import ro.cosminmihu.ktor.monitor.library.domain.GetCallUseCase
import ro.cosminmihu.ktor.monitor.library.domain.GetCallsUseCase
import ro.cosminmihu.ktor.monitor.library.domain.ListenByRecentCallsUseCase
import ro.cosminmihu.ktor.monitor.library.ui.detail.DetailViewModel
import ro.cosminmihu.ktor.monitor.library.ui.notification.NotificationManager
import ro.cosminmihu.ktor.monitor.library.ui.list.ListViewModel

internal fun libraryModule() = listOf(
    coroutineModule,
    databaseModule,
    domainModule,
    notificationModule,
    viewModelModule,
)

internal val databaseModule = module {
    single {
        createDatabase(createDatabaseDriver())
    }
    factoryOf(::LibraryDao)
}

internal val coroutineModule = module {
    single {
        CoroutineScope(Dispatchers.Default + SupervisorJob())
    }
}

internal val notificationModule = module {
    factoryOf(::NotificationManager)
}

internal val viewModelModule = module {
    viewModelOf(::ListViewModel)
    viewModelOf(::DetailViewModel)
}

internal val domainModule = module {
    factoryOf(::ListenByRecentCallsUseCase)
    factoryOf(::GetCallsUseCase)
    factoryOf(::GetCallUseCase)
    factoryOf(::DeleteCallsUseCase)
}