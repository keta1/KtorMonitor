package ro.cosminmihu.ktor.monitor

import androidx.compose.runtime.Composable
import androidx.compose.ui.window.MenuScope
import org.jetbrains.compose.resources.stringResource
import ro.cosminmihu.ktor.monitor.ui.resources.Res
import ro.cosminmihu.ktor.monitor.ui.resources.ktor_tray_name

@Composable
public fun MenuScope.KtorMonitorMenuItem(
    onClick: () -> Unit,
) {
    Item(
        text = stringResource(Res.string.ktor_tray_name),
        onClick = onClick,
    )
}