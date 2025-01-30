package ro.cosminmihu.ktor.monitor

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ro.cosminmihu.ktor.monitor.ui.LibraryUI

/**
 * Ktor Monitor UI entry point.
 */
@Composable
public fun KtorMonitorUI(
    modifier: Modifier = Modifier,
    useKtorMonitorTheme: Boolean = true,
) {
    LibraryUI(
        modifier = modifier,
        useLibraryTheme = useKtorMonitorTheme,
    )
}