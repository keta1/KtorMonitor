package ro.cosminmihu.ktor.monitor.ui.list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.compose.viewmodel.koinViewModel

@Composable
internal fun ListRoute(
    modifier: Modifier = Modifier,
    onClick: (String) -> Unit,
) {
    val viewModel: ListViewModel = koinViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    ListScreen(
        modifier = modifier,
        uiState = uiState,
        setSearchQuery = viewModel::setSearchQuery,
        clearSearchQuery = viewModel::clearSearchQuery,
        deleteCalls = viewModel::deleteCalls,
        onCallClick = onClick,
    )
}