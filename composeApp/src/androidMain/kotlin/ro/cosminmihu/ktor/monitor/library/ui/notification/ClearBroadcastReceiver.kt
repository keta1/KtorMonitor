package ro.cosminmihu.ktor.monitor.library.ui.notification

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import org.koin.core.component.get
import ro.cosminmihu.ktor.monitor.library.di.LibraryKoinComponent
import ro.cosminmihu.ktor.monitor.library.domain.DeleteCallsUseCase

internal class ClearBroadcastReceiver : BroadcastReceiver(), LibraryKoinComponent {

    override fun onReceive(
        context: Context,
        intent: Intent,
    ) {
        get<DeleteCallsUseCase>()()
    }
}
