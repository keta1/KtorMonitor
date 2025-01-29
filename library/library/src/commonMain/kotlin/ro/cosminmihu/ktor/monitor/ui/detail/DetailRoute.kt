package ro.cosminmihu.ktor.monitor.ui.detail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
internal fun DetailRoute(
    id: String?,
    modifier: Modifier = Modifier,
) {
    if (id == null) return

    val viewModel: DetailViewModel = koinViewModel(key = id) { parametersOf(id) }
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    DetailScreen(
        modifier = modifier,
        uiState = uiState,
    )
}