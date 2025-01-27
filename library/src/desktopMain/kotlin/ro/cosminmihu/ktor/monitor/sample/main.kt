package ro.cosminmihu.ktor.monitor.sample

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.window.Tray
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import ro.cosminmihu.ktor.monitor.library.generated.resources.Res
import ro.cosminmihu.ktor.monitor.library.generated.resources.app_name
import ro.cosminmihu.ktor.monitor.library.generated.resources.ic_launcher
import ro.cosminmihu.ktor.monitor.library.ui.KtorMonitorMenuItem
import ro.cosminmihu.ktor.monitor.library.ui.KtorMonitorWindow

fun main() = application {

    var showKtorMonitor by rememberSaveable { mutableStateOf(false) }

    Tray(
        icon = painterResource(Res.drawable.ic_launcher),
        menu = {
            KtorMonitorMenuItem { showKtorMonitor = true }
        }
    )

    KtorMonitorWindow(
        onCloseRequest = { showKtorMonitor = false },
        show = showKtorMonitor
    )

    Window(
        onCloseRequest = ::exitApplication,
        title = stringResource(Res.string.app_name),
    ) {
        App()
    }
}