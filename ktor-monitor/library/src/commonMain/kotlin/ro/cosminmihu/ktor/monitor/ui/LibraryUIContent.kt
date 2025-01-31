package ro.cosminmihu.ktor.monitor.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import org.koin.compose.KoinIsolatedContext
import ro.cosminmihu.ktor.monitor.di.LibraryKoinContext

@Composable
internal fun LibraryUIContent(
    content: @Composable () -> Unit,
) {
    KoinIsolatedContext(
        context = LibraryKoinContext.koinApp
    ) {
        var ready by rememberSaveable { mutableStateOf(false) }
        LaunchedEffect(Unit) {
            LibraryKoinContext.applyPlatformContext()
            ready = true
        }
        if (!ready) return@KoinIsolatedContext

        content()
    }
}