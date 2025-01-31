package ro.cosminmihu.ktor.monitor.ui.notification

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import org.koin.core.component.get
import ro.cosminmihu.ktor.monitor.di.LibraryKoinComponent
import ro.cosminmihu.ktor.monitor.domain.DeleteCallsUseCase

internal class KtorMonitorClearBroadcastReceiver : BroadcastReceiver(), LibraryKoinComponent {

    override fun onReceive(
        context: Context,
        intent: Intent,
    ) {
        get<DeleteCallsUseCase>()()
    }
}
