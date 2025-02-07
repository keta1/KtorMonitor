package ro.cosminmihu.ktor.monitor.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
internal fun LibraryUI(
    modifier: Modifier = Modifier,
    useLibraryTheme: Boolean = true,
) {
    LibraryUIContent {
        if (useLibraryTheme) {
            LibraryTheme {
                LibraryScreen(modifier = modifier.fillMaxSize())
            }
        } else {
            LibraryScreen(modifier = modifier.fillMaxSize())
        }
    }
}
