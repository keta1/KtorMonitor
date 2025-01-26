package ro.cosminmihu.ktor.monitor.library.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Window
import org.jetbrains.compose.resources.stringResource
import ro.cosminmihu.ktor.monitor.composeapp.generated.resources.Res
import ro.cosminmihu.ktor.monitor.composeapp.generated.resources.library_name

@Composable
fun KtorMonitorWindow(
    show: Boolean,
    onCloseRequest: () -> Unit = {},
) {
    if (!show) return

    Window(
        onCloseRequest = onCloseRequest,
        title = stringResource(Res.string.library_name),
    ) {
        KtorMonitorUI(modifier = Modifier.fillMaxSize())
    }
}