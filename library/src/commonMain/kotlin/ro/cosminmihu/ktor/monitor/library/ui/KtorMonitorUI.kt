package ro.cosminmihu.ktor.monitor.library.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.koin.compose.KoinIsolatedContext
import ro.cosminmihu.ktor.monitor.library.di.LibraryKoinContext

@Composable
fun KtorMonitorUI(
    modifier: Modifier = Modifier,
    useLibraryTheme: Boolean = true,
) {
    KoinIsolatedContext(
        context = LibraryKoinContext.koinApp
    ) {
        if (useLibraryTheme) {
            LibraryTheme {
                LibraryScreen(modifier = modifier)
            }
        } else {
            LibraryScreen(modifier = modifier)
        }
    }
}