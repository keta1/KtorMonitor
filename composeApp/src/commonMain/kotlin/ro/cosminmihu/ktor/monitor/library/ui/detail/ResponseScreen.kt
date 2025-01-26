package ro.cosminmihu.ktor.monitor.library.ui.detail

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ResponseScreen(response: DetailUiState.Response, modifier: Modifier = Modifier) {
    CallTypeScreen(
        isLoading = response.isLoading,
        isError = response.isError,
        headers = response.headers,
        body = response.body,
        error = response.error,
        modifier = modifier,
    )
}
