package ro.cosminmihu.ktor.monitor.library.ui.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.stringResource
import ro.cosminmihu.ktor.monitor.composeapp.generated.resources.Res
import ro.cosminmihu.ktor.monitor.composeapp.generated.resources.error

@Composable
fun SummaryScreen(summary: DetailUiState.Summary, modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(vertical = 8.dp, horizontal = 16.dp)) {
        KeyValue(key = "URL", value = summary.url)
        KeyValue(key = "Method", value = summary.method)
        KeyValue(key = "Protocol", value = summary.protocol)
        when {
            summary.isError ->
                KeyValue(
                    key = "Status",
                    imageVector = Icons.Filled.Warning,
                    imageTint = MaterialTheme.colorScheme.error
                )

            else ->
                KeyValue(key = "Response Code", value = summary.responseCode)
        }
        Spacer(modifier = Modifier.padding(8.dp))
        KeyValue(key = "Request time", value = summary.requestTime)
        KeyValue(key = "Response time", value = summary.responseTime)
        KeyValue(key = "Duration", value = summary.duration)
        Spacer(modifier = Modifier.padding(8.dp))
        KeyValue(key = "Request size", value = summary.requestSize)
        KeyValue(key = "Response size", value = summary.responseSize)
        KeyValue(key = "Total Size", value = summary.totalSize)
    }
}

@Composable
private fun KeyValue(key: String, value: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = key,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.weight(0.3f),
            style = MaterialTheme.typography.bodyMedium,
        )
        Text(
            text = value,
            modifier = Modifier.weight(0.7f).padding(start = 8.dp),
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}


@Composable
private fun KeyValue(key: String, imageVector: ImageVector, imageTint: Color) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = key,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.weight(0.3f),
            style = MaterialTheme.typography.bodyMedium,
        )
        Box(modifier = Modifier.weight(0.7f)) {
            Icon(
                imageVector = imageVector,
                contentDescription = stringResource(Res.string.error),
                tint = imageTint,
            )
        }
    }
}