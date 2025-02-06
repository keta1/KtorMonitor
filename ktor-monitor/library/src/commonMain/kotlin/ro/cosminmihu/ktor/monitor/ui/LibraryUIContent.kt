package ro.cosminmihu.ktor.monitor.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DisabledByDefault
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.KoinIsolatedContext
import ro.cosminmihu.ktor.monitor.di.LibraryKoinContext
import ro.cosminmihu.ktor.monitor.ui.resources.Res
import ro.cosminmihu.ktor.monitor.ui.resources.ktor_library_disabled

@Composable
internal fun LibraryUIContent(
    content: @Composable () -> Unit,
) {
    KoinIsolatedContext(
        context = LibraryKoinContext.koinApp
    ) {
        // Check if the library is active.
        val config = LibraryKoinContext.getLibraryConfig()
        when {
            config.isActive -> content()
            else -> EmptyState()
        }
    }
}

@Composable
private fun EmptyState(modifier: Modifier = Modifier) {
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        val string = stringResource(Res.string.ktor_library_disabled)
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.semantics(true) { string },
        ) {
            Icon(
                imageVector = Icons.Default.DisabledByDefault,
                contentDescription = null,
                modifier = Modifier.size(Dimens.ExtraLarge)
            )
            Text(
                text = string,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(top = Dimens.Medium)
            )
        }
    }
}