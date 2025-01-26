package ro.cosminmihu.ktor.monitor.library.ui

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.koin.compose.KoinIsolatedContext
import ro.cosminmihu.ktor.monitor.library.di.LibraryKoinContext

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KtorMonitorUI(
    modifier: Modifier = Modifier,
) {
    LibraryTheme {
        KoinIsolatedContext(
            context = LibraryKoinContext.koinApp
        ) {
            LibraryScreen(modifier = modifier)
        }
    }
}