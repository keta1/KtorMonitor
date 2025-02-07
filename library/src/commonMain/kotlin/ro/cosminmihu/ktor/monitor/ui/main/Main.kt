package ro.cosminmihu.ktor.monitor.ui.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ro.cosminmihu.ktor.monitor.ui.theme.LibraryTheme

@Composable
internal fun Main(
    modifier: Modifier = Modifier,
    useLibraryTheme: Boolean = true,
) {
    MainContent {
        if (useLibraryTheme) {
            LibraryTheme {
                MainScreen(modifier = modifier.fillMaxSize())
            }
        } else {
            MainScreen(modifier = modifier.fillMaxSize())
        }
    }
}
