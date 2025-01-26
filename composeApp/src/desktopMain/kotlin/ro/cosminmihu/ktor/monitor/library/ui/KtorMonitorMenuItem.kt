package ro.cosminmihu.ktor.monitor.library.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.window.MenuScope
import org.jetbrains.compose.resources.stringResource
import ro.cosminmihu.ktor.monitor.composeapp.generated.resources.Res
import ro.cosminmihu.ktor.monitor.composeapp.generated.resources.tray_name

@Composable
fun MenuScope.KtorMonitorMenuItem(
    onClick: () -> Unit,
) {
    Item(
        text = stringResource(Res.string.tray_name),
        onClick = onClick,
    )
}