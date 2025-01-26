package ro.cosminmihu.ktor.monitor.library.ui.detail

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun RequestScreen(request: DetailUiState.Request, modifier: Modifier = Modifier) {
    CallTypeScreen(
        isLoading = false,
        isError = false,
        headers = request.headers,
        body = request.body,
        error = "",
        modifier = modifier,
    )
}